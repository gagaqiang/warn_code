package com.service.warn;

import com.dao.erpreport.AlipayAccountOrderMapper;
import com.dao.erptc.TradeRefundMapper;
import com.ds.DS;
import com.entity.erpreport.AlipayAccountOrder;
import com.entity.erptc.ErrorMsg;
import com.entity.erptc.TradeInvoiceHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auther: hsl
 * @Date: 2018/10/9 10:15
 * @Description: 支付宝流水
 */
@Service
public class TcAlipayAccountOrderService {

    public final static Logger _logger = LoggerFactory.getLogger(TcAlipayAccountOrderService.class);

    @Autowired
    private AlipayAccountOrderMapper alipayAccountOrderMapper;
    @Autowired
    private TradeRefundMapper tradeRefundMapper;

    /**
     * 流水入账,SAP超24小时未返回(sign = 4)
     * 流水入账,IMS超24小时未返回(sign = 2)
     */
    @DS(value = "erp_report")
    public List<ErrorMsg> findImsOrSapOutTimeAndError(String code, String name,
                                                      Integer sign, int hour) {

        List<AlipayAccountOrder> list = alipayAccountOrderMapper.findImsOrSapOutTimeAndError(sign, getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (AlipayAccountOrder alipayAccountOrder : list) {
                ErrorMsg errorMsg = new ErrorMsg();
                errorMsg.setCode(code);
                errorMsg.setName(name);
                errorMsg.setCreateDate(new Date());
                errorMsg.setIsDeal(0);
                errorMsg.setPid(alipayAccountOrder.getId());
                errorMsg.setPlatformCode(alipayAccountOrder.getPlatformNo());
                errorMsg.setErrorDetail(alipayAccountOrder.getImsPushMsg());
                errorMsg.setShopId(Math.toIntExact(alipayAccountOrder.getShopId()));
                errorMsgList.add(errorMsg);
            }
        }
        return errorMsgList;
    }

    @DS(value = "erp_report")
    public List<AlipayAccountOrder> listMarginOrder(int hour) {
        return alipayAccountOrderMapper.selectMarginOrder(getStartDate(hour));
    }

    /**
     * 支付宝流水【账务类型=转账】手工入账
     * @param code
     * @param name
     * @param hour
     * @return
     */
    @DS(value = "erp_report")
    public List<ErrorMsg> listTransferOrderToErrorMsg(String code, String name,
                                                      int hour) {

        List<AlipayAccountOrder> list = alipayAccountOrderMapper.selectTransferOrder(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (AlipayAccountOrder alipayAccountOrder : list) {
                ErrorMsg errorMsg = new ErrorMsg();
                errorMsg.setCode(code);
                errorMsg.setName(name);
                errorMsg.setCreateDate(new Date());
                errorMsg.setIsDeal(0);
                errorMsg.setPid(alipayAccountOrder.getId());
                errorMsg.setPlatformCode(alipayAccountOrder.getPlatformNo());
                errorMsg.setErrorDetail(alipayAccountOrder.getImsPushMsg());
                errorMsg.setShopId(Math.toIntExact(alipayAccountOrder.getShopId()));
                errorMsgList.add(errorMsg);
            }
        }
        return errorMsgList;
    }

    /**
     * 支付宝流水【账务类型=集分宝】手工入账预警
     * @param code
     * @param name
     * @param hour
     * @return
     */
    @DS(value = "erp_report")
    public List<ErrorMsg> listJFBOrderToErrorMsg(String code, String name,
                                                      int hour) {

        List<AlipayAccountOrder> list = alipayAccountOrderMapper.selectJFBOrder(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (AlipayAccountOrder alipayAccountOrder : list) {
                ErrorMsg errorMsg = new ErrorMsg();
                errorMsg.setCode(code);
                errorMsg.setName(name);
                errorMsg.setCreateDate(new Date());
                errorMsg.setIsDeal(0);
                errorMsg.setPid(alipayAccountOrder.getId());
                errorMsg.setPlatformCode(alipayAccountOrder.getPlatformNo());
                errorMsg.setErrorDetail(alipayAccountOrder.getImsPushMsg());
                errorMsg.setShopId(Math.toIntExact(alipayAccountOrder.getShopId()));
                errorMsgList.add(errorMsg);
            }
        }
        return errorMsgList;
    }

    @DS(value = "erp_tc")
    public List<TradeInvoiceHeader> listMarginRefund(String platformCode) {
        return tradeRefundMapper.selectMarginRefund(platformCode);
    }


    private String getStartDate(int hour) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return sdf.format(calendar.getTime());
    }

}
