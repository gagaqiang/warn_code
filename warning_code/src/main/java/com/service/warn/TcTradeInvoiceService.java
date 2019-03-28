package com.service.warn;

import com.dao.erptc.TradeInvoiceMapper;
import com.ds.DS;
import com.entity.erptc.ErrorMsg;
import com.entity.erptc.TradeInvoiceHeader;
import com.entity.erptc.TradeOrderHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TcTradeInvoiceService {

    public final static Logger _logger = LoggerFactory.getLogger(TcTradeInvoiceService.class);

    @Autowired
    private TradeInvoiceMapper tradeInvoiceMapper;

    /**
     * 发票接口，IMS、SAP超24小时未返回
     * @param code
     * @param name
     * @param imsOrderPushSign  2:ims超时未返回,4:sap超时未返回
     * @param hour
     * @return
     */
    @DS(value = "erp_tc")
    public List<ErrorMsg> mapperTradeInvoiceToErrorMsg(String code, String name,
                                                     List imsOrderPushSign, int hour) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeInvoiceMapper.listByImsInvoicePushSign(imsOrderPushSign, getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
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
        return errorMsgList;
    }

    /**
     *  航信开票超24小时未返回 和 阿里开票超24小时未返回
     * @param code
     * @param name
     * @param hour
     * @return
     */
    public List<ErrorMsg> mapperTradeInvoiceByInvoiceStatus(String code, String name, int hour) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeInvoiceMapper.listByInvoiceStatus(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
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
        return errorMsgList;
    }

    /**
     * 纸质发票超时未开
     * @param code
     * @param name
     * @param hour
     * @return
     */
    public List<ErrorMsg> mapperTradeInvoiceHasSignNoPushImsForPaper(String code, String name, int hour) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeInvoiceMapper.listHasSignNoPushImsForPaper(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
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
        return errorMsgList;
    }

    /**
     * 电子发票超时未开
     * @param code
     * @param name
     * @param hour
     * @return
     */
    public List<ErrorMsg> mapperTradeInvoiceHasSignNoPushImsForElectronic(String code, String name, int hour) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeInvoiceMapper.listHasSignNoPushImsForElectronic(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
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
        return errorMsgList;
    }

    /**
     * 金额正常而开票金额为0
     * @param code
     * @param name
     * @param hour
     * @return
     */
    public List<ErrorMsg> listNoCreateInvoiceOrder(String code, String name, int hour) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeInvoiceMapper.listNoCreateInvoiceOrder(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
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
        return errorMsgList;
    }

    /**
     * 超时N天未开88888发票号
     * @param code
     * @param name
     * @param hour
     * @return
     */
    public List<ErrorMsg> listNoCreateOrderNumber(String code, String name, int hour) {

        List<TradeInvoiceHeader> tradeInvoiceHeaderList = tradeInvoiceMapper.listNoCreateOrderNumber(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeInvoiceHeaderList != null && tradeInvoiceHeaderList.size() > 0) {
            for (TradeInvoiceHeader tradeInvoiceHeader : tradeInvoiceHeaderList) {
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
        return errorMsgList;
    }

    private String getStartDate(int hour) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return sdf.format(calendar.getTime());
    }
}
