package com.dao.erptc;

import com.entity.erptc.TradeInvoiceHeader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeInvoiceMapper {

    /**
     * 根据传ims 状态值和时间段查询符合条件的发票
     * @param imsInvoicePushSign
     * @param startDate
     * @return
     */
    List<TradeInvoiceHeader> listByImsInvoicePushSign(@Param(value = "imsInvoicePushSign") List imsInvoicePushSign,
                                                      @Param(value = "startDate") String startDate);

    List<TradeInvoiceHeader> listByInvoiceStatus(@Param(value = "startDate") String startDate);

    List<TradeInvoiceHeader> listHasSignNoPushImsForPaper(@Param(value = "startDate") String startDate);

    List<TradeInvoiceHeader> listHasSignNoPushImsForElectronic(@Param(value = "startDate") String startDate);

    /**
     * 获取开票金额为0的平台单号
     * @param startDate
     * @return
     */
    List<TradeInvoiceHeader> listNoCreateInvoiceOrder(@Param(value = "startDate") String startDate);

    List<TradeInvoiceHeader> listNoCreateOrderNumber(@Param(value = "startDate") String startDate);
}
