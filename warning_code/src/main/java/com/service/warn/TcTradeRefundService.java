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
import java.util.*;

/**
 * @Auther: hsl
 * @Date: 2018/9/29 11:44
 * @Description:
 */
@Service
public class TcTradeRefundService {

    public final static Logger _logger = LoggerFactory.getLogger(TcTradeRefundService.class);

    @Autowired
    private TradeRefundMapper tradeRefundMapper;
    @Autowired
    private TcAlipayAccountOrderService alipayAccountOrderService;
    @Autowired
    private WarningDetailService warningDetailService;


    /**
     * 退款单正确，超24小时未自动结算
     *
     * @param code
     * @param name
     * @return
     */
    @DS(value = "erp_tc")
    public List<ErrorMsg> listNoAgreeRefund(String code, String name, int endhour, int startDate) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeRefundMapper.listNoAgreeRefund(getStartDate(endhour), getStartDate(startDate));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
                int count = warningDetailService.getDetailIdByPlatfromCodeAndCode(code, tradeInvoiceHeader.getPlatfromCode());
                if (count == 0) {
                    ErrorMsg errorMsg = new ErrorMsg();
                    errorMsg.setCode(code);
                    errorMsg.setName(name);
                    errorMsg.setCreateDate(new Date());
                    errorMsg.setIsDeal(0);
                    errorMsg.setPid(tradeInvoiceHeader.getId());
                    errorMsg.setPlatformCode(tradeInvoiceHeader.getPlatfromCode());
                    errorMsg.setErrorDetail(tradeInvoiceHeader.getImsInvoicePushMsg());
                    errorMsg.setShopId(Math.toIntExact(tradeInvoiceHeader.getShopId()));
                    errorMsgList.add(errorMsg);
                }
            }
        }
        return errorMsgList;
    }

    /**
     * 退款单（种类、数量）错误
     *
     * @param code
     * @param name
     * @return
     */
    @DS(value = "erp_tc")
    public List<ErrorMsg> listErrorTypeRefund(String code, String name, int endhour, int startDate) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeRefundMapper.listErrorTypeRefund(getStartDate(endhour),getStartDate(startDate));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
                //int count = warningDetailService.getDetailIdByPlatfromCodeAndCode(code, tradeInvoiceHeader.getPlatfromCode());
                //if(count==0){
                    ErrorMsg errorMsg = new ErrorMsg();
                    errorMsg.setCode(code);
                    errorMsg.setName(name);
                    errorMsg.setCreateDate(new Date());
                    errorMsg.setIsDeal(0);
                    errorMsg.setPid(tradeInvoiceHeader.getId());
                    errorMsg.setPlatformCode(tradeInvoiceHeader.getPlatfromCode());
                    errorMsg.setErrorDetail(tradeInvoiceHeader.getImsInvoicePushMsg());
                    errorMsg.setShopId(Math.toIntExact(tradeInvoiceHeader.getShopId()));
                    errorMsgList.add(errorMsg);
                //}
            }
        }
        return errorMsgList;
    }


    /**
     * N小时内 保证金未有对应退款的的预警
     *
     * @param code
     * @param name
     * @param hour
     * @return
     */
    public List<ErrorMsg> listMarginRefundNoCreate(String code, String name, int hour) {
        List<AlipayAccountOrder> alipayAccountOrderList = alipayAccountOrderService.listMarginOrder(hour);
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (alipayAccountOrderList != null && alipayAccountOrderList.size() > 0) {
            for (AlipayAccountOrder alipayAccountOrder : alipayAccountOrderList) {
                List<TradeInvoiceHeader> tradeInvoiceHeaderList = alipayAccountOrderService.listMarginRefund(alipayAccountOrder.getPlatformNo());
                if (tradeInvoiceHeaderList == null || tradeInvoiceHeaderList.size() == 0) {
                    ErrorMsg errorMsg = new ErrorMsg();
                    errorMsg.setCode(code);
                    errorMsg.setName(name);
                    errorMsg.setCreateDate(new Date());
                    errorMsg.setIsDeal(0);
                    errorMsg.setPid(alipayAccountOrder.getId());
                    errorMsg.setPlatformCode(alipayAccountOrder.getPlatformNo());
                    errorMsg.setErrorDetail("");
                    errorMsg.setShopId(Math.toIntExact(alipayAccountOrder.getShopId()));
                    errorMsgList.add(errorMsg);
                } else {
                    boolean flag = false;
                    for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
                        if (Objects.equals(tradeInvoiceHeader.getAmount(), alipayAccountOrder.getOutAmount())) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        ErrorMsg errorMsg = new ErrorMsg();
                        errorMsg.setCode(code);
                        errorMsg.setName(name);
                        errorMsg.setCreateDate(new Date());
                        errorMsg.setIsDeal(0);
                        errorMsg.setPid(alipayAccountOrder.getId());
                        errorMsg.setPlatformCode(alipayAccountOrder.getPlatformNo());
                        errorMsg.setErrorDetail("");
                        errorMsg.setShopId(Math.toIntExact(alipayAccountOrder.getShopId()));
                        errorMsgList.add(errorMsg);
                    }
                }
            }
        }
        return errorMsgList;
    }

    private String getStartDate(int hour) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return sdf.format(calendar.getTime());
    }
}
