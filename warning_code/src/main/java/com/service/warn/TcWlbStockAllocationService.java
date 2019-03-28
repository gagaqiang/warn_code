package com.service.warn;

import com.dao.erpStock.WlbStockAllocationMapper;
import com.ds.DS;
import com.entity.erpStock.WlbStockAllocation;
import com.entity.erptc.ErrorMsg;
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
 * @Date: 2018/10/19 13:37
 * @Description:
 */
@Service
public class TcWlbStockAllocationService {

    public final static Logger _logger = LoggerFactory.getLogger(TcTradeRefundService.class);

    @Autowired
    private WlbStockAllocationMapper wlbStockAllocationMapper;
    @Autowired
    private WarningDetailService warningDetailService;


    /**
     * 大仓之间的调拨
     */
    @DS(value = "erp_stock")
    public List<ErrorMsg> select(String code, String name, int hour) {

        List<WlbStockAllocation> list = wlbStockAllocationMapper.select(getStartDate(hour));
        List<ErrorMsg> errorMsgList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (WlbStockAllocation wlbStockAllocation : list) {
                //int count = warningDetailService.getDetailIdByPlatfromCodeAndCode(code, wlbStockAllocation.getAllocationOrderCode());
                //if (count == 0) {
                    ErrorMsg errorMsg = new ErrorMsg();
                    errorMsg.setCode(code);
                    errorMsg.setName(name);
                    errorMsg.setCreateDate(new Date());
                    errorMsg.setIsDeal(0);
                    errorMsg.setPid(wlbStockAllocation.getId());
                    errorMsg.setPlatformCode(wlbStockAllocation.getAllocationOrderCode());
                    errorMsg.setErrorDetail("大仓之间的调拨");
                    errorMsg.setShopId(null);
                    errorMsgList.add(errorMsg);
                //}
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
