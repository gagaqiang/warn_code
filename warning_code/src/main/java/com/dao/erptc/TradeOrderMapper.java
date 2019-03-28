package com.dao.erptc;

import com.entity.erptc.TradeOrderHeader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeOrderMapper {

    /**
     * 根据传ims 状态值和时间段查询符合条件的销售订单
     * @param imsOrderPushSign
     * @param startDate
     * @return
     */
    List<TradeOrderHeader> listByImsOrderPushSign(@Param(value = "imsOrderPushSign") List imsOrderPushSign,
                                                  @Param(value = "startDate") String startDate);

    /**
     * 根据传ims 状态值和时间段查询符合条件的退货订单
     * @param imsOrderPushSign
     * @param startDate
     * @return
     */
    List<TradeOrderHeader> listByImsReturnOrderPushSign(@Param(value = "imsOrderPushSign") List imsOrderPushSign,
                                                  @Param(value = "startDate") String startDate);

    /**
     * 查下45天前未转换的异常退货单
     * @return
     */
    List<TradeOrderHeader> listExceptionReturnOrder();
}
