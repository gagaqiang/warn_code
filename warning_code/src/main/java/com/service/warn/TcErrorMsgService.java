package com.service.warn;

import com.dao.erptc.ErrorMsgMapper;
import com.dao.erptc.PointHeaderMapper;
import com.ds.DS;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.WarningHeader;
import com.enums.ShopEnum;
import com.enums.WarningEnum;
import com.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TcErrorMsgService {

    public final static Logger _logger = LoggerFactory.getLogger(TcErrorMsgService.class);

    private final static Integer HOURS_0 = 0;       //0小时
    private final static Integer HOURS_1 = 1;       //1小时
    private final static Integer HOURS_6 = 6;       //6小时
    private final static Integer HOURS_24 = 24;     //24小时
    private final static Integer HOURS_48 = 48;     //48小时
    private final static Integer HOURS_DAY_6 = 6*24;       //6小时
    private final static Integer HOURS_DAY_15 = 360;//15天
    private final static Integer HOURS_DAY_30 = 720;//30天

    @Autowired
    private TcTradeInvoiceService tcTradeInvoiceService;
    @Autowired
    private WarningHeaderService warningHeaderService;
    @Autowired
    private ErrorMsgMapper errorMsgMapper;
    @Autowired
    private WarningGroupService warningGroupService;
    @Autowired
    private TcTradeOrderService tcTradeOrderService;
    @Autowired
    private PointHeaderMapper pointHeaderMapper;
    @Autowired
    private TcTradeRefundService tcTradeRefundService;
    @Autowired
    private TcAlipayAccountOrderService tcAlipayAccountOrderService;
    @Autowired
    private TcWlbStockAllocationService tcWlbStockAllocationService;
    @Autowired
    private WarningDetailService warningDetailService;

    /**
     * 同步TC数据
     *
     * @param warnCode
     * @param platformCode
     */
    @DS(value = "erp_tc")
    public void updateDeal(String warnCode, String platformCode) {
        errorMsgMapper.updateIsDeal(warnCode, platformCode);
    }


    /**
     * 判断上个月订单是否导入
     */
    public boolean getIntegralOrderNotImportCount() {
        Integer num = pointHeaderMapper.getCountByMonth(DateUtil.getLastMonth());
        if (num == 0) {
            return true;
        }
        return false;
    }


    /**
     * 抓取错误数据
     *
     * @param warnCode     预警项code
     * @param isDetailOnly 是否单一详情数据
     * @throws InterruptedException
     */
    @DS(value = "erp_tc")
    public void dealTcErrorMsg(String warnCode, boolean isDetailOnly)  {
        try {
            List<ErrorMsg> allErrors = errorMsgMapper.getErrorMsgByParmes(warnCode, null, DateUtil.getTime(15));
            if (CollectionUtils.isEmpty(allErrors)) {
                _logger.info(warnCode + "没有抓取到错误信息......");
            } else {
                List<ErrorMsg> hasdoMsg = new ArrayList<>();
                List<ErrorMsg> undoMsg = new ArrayList<>();
                for (ErrorMsg err : allErrors) {
                    //过滤掉不需要处理的店铺
                    if (null != err.getShopId()) {
                        if (null == ShopEnum.getName(err.getShopId())) {
                            continue;
                        }
                    }
                    if (err.getIsDeal() == 1) {
                        hasdoMsg.add(err);
                    } else {
                        undoMsg.add(err);
                    }
                }
                WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
                if (wh != null) {
                    //未处理
                    dealUndoMsg(undoMsg, wh, isDetailOnly);
                    //已经处理
                    dealHasdoMsgs(hasdoMsg, wh, true);
                } else {
                    _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
                }
            }
        } catch (Exception e) {
            _logger.error("读取错误信息失败 ！！！！" + e);
        }
    }


    /**
     * 45天前异常退货单
     * @param warnCode
     */
    public void listExceptionReturnOrder(String warnCode){
        List<ErrorMsg> tradeOrders = tcTradeOrderService.listExceptionReturnOrder(warnCode, WarningEnum.getName(warnCode));

        updateWarndetail(warnCode, tradeOrders);

        /*if (CollectionUtils.isEmpty(tradeOrders)) {
            _logger.info("----没有45天前异常退货单----");
        } else {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(tradeOrders, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/

    }




    /**
     * 未生成888
     * @param warnCode
     */
    public void dealOrder888TimeoutMsg(String warnCode){
        List<ErrorMsg> tradeOrders = tcTradeInvoiceService.listNoCreateOrderNumber(warnCode, WarningEnum.getName(warnCode), HOURS_DAY_6);
        updateWarndetail(warnCode, tradeOrders);

        /*if (CollectionUtils.isEmpty(tradeOrders)) {
            _logger.info("----没有未生成888的订单----");
        } else {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(tradeOrders, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }




    /**
     * 处理超时的订单
     * 销售订单，IMS、SAP超24小时未返回
     *
     * @param type    2:IMS,  4:SAP
     * @param warnCode
     */
    public void dealOrderTimeOutMsg(String warnCode, int type) {
        List<Integer> li = new ArrayList<>();
        li.add(type);

        List<ErrorMsg> tradeOrders = tcTradeOrderService.mapperTradeOrderToErrorMsg(warnCode
                , WarningEnum.getName(warnCode), li, HOURS_24);
        updateWarndetail(warnCode, tradeOrders);

        /*if (CollectionUtils.isEmpty(tradeOrders) ) {
            _logger.info("----没有超时的订单----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(tradeOrders);

            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }

        }*/
    }

    /**
     * 处理超时的退货单
     * 退货单，IMS、SAP超24小时未返回
     * @param  type 2:IMS,  4:SAP
     * @param warnCode
     */
    public void dealReturnTimeOutMsg(String warnCode, int type) {
        List<Integer> li = new ArrayList<>();
        li.add(type);

        List<ErrorMsg> returnOrders = tcTradeOrderService.mapperTradeReturnOrderToErrorMsg(warnCode
                , WarningEnum.getName(warnCode), li, DateUtil.getTime(1), HOURS_24);
        updateWarndetail(warnCode, returnOrders);
        /*if (CollectionUtils.isEmpty(returnOrders)) {
            _logger.info("----没有超时的退货单----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(returnOrders);

            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }

        }*/
    }


    /**
     * 处理超时的发票
     * 发票接口，IMS、SAP超24小时未返回
     *
     * @param warnCode
     * @param type 2:IMS、4:SAP
     */
    public void dealInvoiceTimeOutMsg(String warnCode, int type) {
        List<Integer> li = new ArrayList<>();
        li.add(type);

        List<ErrorMsg> invoiceList = tcTradeInvoiceService.mapperTradeInvoiceToErrorMsg(warnCode
                , WarningEnum.getName(warnCode), li, HOURS_24);

        updateWarndetail(warnCode, invoiceList);

        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有IMS、SAP超24小时未返回的发票----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }

    /**
     * 处理超时的发票
     * 航信或者阿里开票超24小时未返回
     *
     * @param warnCode
     */
    public void dealInvoiceTimeOutMsg2(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeInvoiceService.mapperTradeInvoiceByInvoiceStatus(warnCode
                , WarningEnum.getName(warnCode), HOURS_24);
        updateWarndetail(warnCode, invoiceList);

        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有航信或者阿里开票超24小时未返回的发票----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }

    /**
     * 处理超时的发票
     * 已签收订单纸票开票超30天未开预警
     *
     * @param warnCode
     */
    public void mapperTradeInvoiceHasSignNoPushImsForPaper(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeInvoiceService.mapperTradeInvoiceHasSignNoPushImsForPaper(warnCode
                , WarningEnum.getName(warnCode), HOURS_DAY_30);

        updateWarndetail(warnCode, invoiceList);
    }


    /**
     * 处理超时的发票
     * 已签收订单电子发票开票超15天未开预警
     *
     * @param warnCode
     */
    public void mapperTradeInvoiceHasSignNoPushImsForElectronic(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeInvoiceService.mapperTradeInvoiceHasSignNoPushImsForElectronic(warnCode
                , WarningEnum.getName(warnCode), HOURS_DAY_15);

        updateWarndetail(warnCode, invoiceList);
    }





    /**
     * 退款单正确，超24小时未自动结算
     *
     * @param warnCode
     */
    public void listNoAgreeRefund(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeRefundService.listNoAgreeRefund(warnCode
                , WarningEnum.getName(warnCode), HOURS_24,HOURS_48);
        updateWarndetail(warnCode, invoiceList);
        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有退款单正确，超24小时未自动结算信息----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }

    /**
     * 退款单（种类、数量）错误
     *
     * @param warnCode
     */
    public void listErrorTypeRefund(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeRefundService.listErrorTypeRefund(warnCode
                , WarningEnum.getName(warnCode), HOURS_48,HOURS_0);
        updateWarndetail(warnCode, invoiceList);
        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有退款单（种类、数量）错误信息----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }



    /**
     * 金额正常而开票金额为0
     *
     * @param warnCode
     */
    public void listNoCreateInvoiceOrder(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeInvoiceService.listNoCreateInvoiceOrder(warnCode
                , WarningEnum.getName(warnCode), HOURS_0);

        updateWarndetail(warnCode, invoiceList);
    }

    /**
     * 流水入账,IMS超24小时未返回(sign = 2)
     *
     * @param warnCode
     */
    public void dealFlowingImsOutTimeOrError(String warnCode) {

        List<ErrorMsg> invoiceList = tcAlipayAccountOrderService.findImsOrSapOutTimeAndError(warnCode
                , WarningEnum.getName(warnCode), 2, HOURS_24);
        updateWarndetail(warnCode, invoiceList);
        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有IMS超24小时未返回的流水入账信息----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }

    /**
     * 流水入账,SAP超24小时未返回(sign = 4)
     *
     * @param warnCode
     */
    public void dealFlowingSapOutTimeOrError(String warnCode) {

        List<ErrorMsg> invoiceList = tcAlipayAccountOrderService.findImsOrSapOutTimeAndError(warnCode
                , WarningEnum.getName(warnCode), 4, HOURS_24);
        updateWarndetail(warnCode, invoiceList);
        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有SAP超24小时未返回的流水入账信息----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }

    /**
     * 保证金退款
     * @param warnCode
     */
    public void listMarginOrder(String warnCode) {

        List<ErrorMsg> invoiceList = tcTradeRefundService.listMarginRefundNoCreate(warnCode
                , WarningEnum.getName(warnCode), HOURS_24);
        updateWarndetail(warnCode, invoiceList);
        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有保证金退款信息----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }

    /**
     * 支付宝流水【账务类型=转账】手工入账
     * @param warnCode
     */
    public void listTransferOrderToErrorMsg(String warnCode) {

        List<ErrorMsg> invoiceList = tcAlipayAccountOrderService.listTransferOrderToErrorMsg(warnCode
                , WarningEnum.getName(warnCode), HOURS_1);
        if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有支付宝流水【账务类型=转账】手工入账信息----");
        } else {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(invoiceList, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }
    }

    /**
     * 支付宝流水【账务类型=集分宝】手工入账
     * @param warnCode
     */
    public void listJFBOrderToErrorMsg(String warnCode) {

        List<ErrorMsg> invoiceList = tcAlipayAccountOrderService.listJFBOrderToErrorMsg(warnCode
                , WarningEnum.getName(warnCode), HOURS_1);
        if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有支付宝流水【账务类型=集分宝】手工入账信息----");
        } else {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(invoiceList, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }
    }

    /**
     * 大仓之间的调拨
     *
     * @param warnCode
     */
    public void select(String warnCode) {

        List<ErrorMsg> invoiceList = tcWlbStockAllocationService.select(warnCode
                , WarningEnum.getName(warnCode),HOURS_48);
        updateWarndetail(warnCode, invoiceList);
        /*if (CollectionUtils.isEmpty(invoiceList)) {
            _logger.info("----没有大仓之间的调拨信息----");
        } else {
            List<ErrorMsg> all = new ArrayList<ErrorMsg>();
            all.addAll(invoiceList);
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (wh != null) {
                dealUndoMsg(all, wh, false);
            } else {
                _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
            }
        }*/
    }


    /**
     * 统一处理TC无法完成的单子
     * @param warnCode
     * @param invoiceList
     */
    private void updateWarndetail(String warnCode, List<ErrorMsg> invoiceList){
        WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
        if(null != wh){
            //全部完成
            if (CollectionUtils.isEmpty(invoiceList)) {
                List<ErrorMsg> errList = getTCInWarningMsg(warnCode);
                if (CollectionUtils.isNotEmpty(errList))
                    dealHasdoMsgs(errList, wh, false);
            } else {
                //当前预警表已存在的预警数据---表示已处理完成
                List<ErrorMsg> errCodes = getWarnplatformCodeList(warnCode, invoiceList);
                dealHasdoMsgs(errCodes, wh, false);

                //处理当前数据
                dealUndoMsg(invoiceList, wh, false);
            }
        }else{
            _logger.info("预警编号：<" + warnCode + " 的预警项没有、或已暂停> !!!");
        }
    }



    /**
     * 获取已存的未处理数据
     * @param warnCode
     * @return
     */
    private List<ErrorMsg> getTCInWarningMsg(String warnCode){
        return warningDetailService.getTcInWarningMsg(warnCode);
    }



    /**
     * 获取不在当前数据内的数据
     * @param errlist
     * @return
     */
    private List<ErrorMsg> getWarnplatformCodeList(String wardCode, List<ErrorMsg> errlist){
        List<String> li = new ArrayList<>();
        for (ErrorMsg err:errlist){
            li.add(err.getPlatformCode());
        }
        return warningDetailService.getUnDoTcInWarningMsg(wardCode, li);
    }



    /**
     * 已处理
     *
     * @param errorDealList
     * @param wh
     * @param flag true:error表数据，false:warn数据
     */
    private void dealHasdoMsgs(List<ErrorMsg> errorDealList, WarningHeader wh, boolean flag) {
        if (CollectionUtils.isNotEmpty(errorDealList)) {
            int num = errorDealList.size();
            _logger.info(wh.getCode() + " : " + ":已经处理完成的 数量>>>:" + num);
            for (ErrorMsg err : errorDealList) {
                warningGroupService.updateHasPlatfromCodeStatus(err, wh, flag);
            }
        } else {
            _logger.info("没有已经处理完成: " + wh.getCode() + " : " + WarningEnum.getName(wh.getCode()));
        }
    }


    /**
     * 未处理
     *
     * @param errorList
     * @param wh
     */
    private void dealUndoMsg(List<ErrorMsg> errorList, WarningHeader wh, boolean isDetailOnly) {
        String warnCode = wh.getCode();
        if (CollectionUtils.isNotEmpty(errorList)) {
            _logger.info(warnCode + " : " + WarningEnum.getName(warnCode) + " :未处理完成的数量>>>:" + errorList.size());
            for (ErrorMsg err : errorList) {
                _logger.info("预警CODE==" + warnCode + ", 单号: " + err.getPlatformCode() + ", 店铺: " + err.getShopId());
                //加入预警明细
                warningGroupService.addWarningGroup(wh, err, isDetailOnly);
            }
        } else {
            _logger.info("没有未处理的：" + warnCode + " : " + WarningEnum.getName(warnCode));
        }
    }

}
