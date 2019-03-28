package com.service.warn;

import com.dao.erptc.TradeOrderMapper;
import com.ds.DS;
import com.entity.erptc.ErrorMsg;
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
public class TcTradeOrderService {

    public final static Logger _logger = LoggerFactory.getLogger(TcTradeOrderService.class);

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    /**
     * 销售超时
     * @param code
     * @param name
     * @param imsOrderPushSign  2:ims超时未返回,4:sap超时未返回
     * @param hour
     * @return
     */
    @DS(value = "erp_tc")
    public List<ErrorMsg> mapperTradeOrderToErrorMsg(String code, String name,
                                                     List imsOrderPushSign, int hour) {

        List<TradeOrderHeader> tradeOrderHeaderList = tradeOrderMapper.listByImsOrderPushSign(imsOrderPushSign, getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeOrderHeaderList != null && tradeOrderHeaderList.size() > 0) {
            for (TradeOrderHeader tradeOrderHeader : tradeOrderHeaderList) {
                ErrorMsg errorMsg = new ErrorMsg();
                errorMsg.setCode(code);
                errorMsg.setName(name);
                errorMsg.setCreateDate(new Date());
                errorMsg.setIsDeal(0);
                errorMsg.setPid(tradeOrderHeader.getId());
                errorMsg.setPlatformCode(tradeOrderHeader.getPlatformCode());
                errorMsg.setErrorDetail(tradeOrderHeader.getImsOrderPushMsg());
                errorMsg.setShopId(Math.toIntExact(tradeOrderHeader.getShopId()));
                errorMsgList.add(errorMsg);
            }
        }
        return errorMsgList;
    }


    /**
     * 退货超时
     * @param code
     * @param name
     * @param imsOrderPushSign 2:ims超时未返回,4:sap超时未返回
     * @param startDate
     * @param hour
     * @return
     */
    @DS(value = "erp_tc")
    public List<ErrorMsg> mapperTradeReturnOrderToErrorMsg(String code, String name,
                                                     List imsOrderPushSign, String startDate, int hour) {
        List<TradeOrderHeader> tradeOrderHeaderList = tradeOrderMapper.listByImsReturnOrderPushSign(imsOrderPushSign, getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeOrderHeaderList != null && tradeOrderHeaderList.size() > 0) {
            for (TradeOrderHeader tradeOrderHeader : tradeOrderHeaderList) {
                ErrorMsg errorMsg = new ErrorMsg();
                errorMsg.setCode(code);
                errorMsg.setName(name);
                errorMsg.setCreateDate(new Date());
                errorMsg.setIsDeal(0);
                errorMsg.setPid(tradeOrderHeader.getId());
                errorMsg.setPlatformCode(tradeOrderHeader.getPlatformCode());
                errorMsg.setErrorDetail(tradeOrderHeader.getImsOrderPushMsg());
                errorMsg.setShopId(Math.toIntExact(tradeOrderHeader.getShopId()));
                errorMsgList.add(errorMsg);
            }
        }
        return errorMsgList;
    }

    /**
     * 查询45天前异常退货单
     * @param code
     * @param name
     * @return
     */
    @DS(value = "erp_tc")
    public List<ErrorMsg> listExceptionReturnOrder(String code, String name) {
        List<TradeOrderHeader> tradeOrderHeaderList = tradeOrderMapper.listExceptionReturnOrder();
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (tradeOrderHeaderList != null && tradeOrderHeaderList.size() > 0) {
            for (TradeOrderHeader tradeOrderHeader : tradeOrderHeaderList) {
                ErrorMsg errorMsg = new ErrorMsg();
                errorMsg.setCode(code);
                errorMsg.setName(name);
                errorMsg.setCreateDate(new Date());
                errorMsg.setIsDeal(0);
                errorMsg.setPid(tradeOrderHeader.getId());
                errorMsg.setPlatformCode(tradeOrderHeader.getPlatformCode());
                errorMsg.setErrorDetail(tradeOrderHeader.getImsOrderPushMsg());
                errorMsg.setShopId(Math.toIntExact(tradeOrderHeader.getShopId()));
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
