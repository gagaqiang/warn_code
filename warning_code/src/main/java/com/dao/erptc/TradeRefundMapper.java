package com.dao.erptc;


import com.entity.erptc.TradeInvoiceHeader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeRefundMapper {

    /**
     * 退款单未自动完结的平台单号
     * @param startDate
     * @return
     */
    List<TradeInvoiceHeader> listNoAgreeRefund(@Param(value = "endDate") String endDate,@Param(value = "startDate") String startDate);

    /**
     * 退款单（种类、数量）错误
     * @param startDate
     * @return
     */
    List<TradeInvoiceHeader> listErrorTypeRefund(@Param(value = "endDate") String endDate,@Param(value = "startDate") String startDate);

    List<TradeInvoiceHeader> selectMarginRefund(@Param(value = "platfromCode") String platfromCode);
}
