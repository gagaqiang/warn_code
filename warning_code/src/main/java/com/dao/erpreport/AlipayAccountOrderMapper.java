package com.dao.erpreport;

import com.entity.erpreport.AlipayAccountOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlipayAccountOrderMapper {

    /**
     * 流水入账,SAP超24小时未返回(sign = 4)
     * 流水入账,IMS超24小时未返回(sign = 2)
     */
    List<AlipayAccountOrder> findImsOrSapOutTimeAndError(@Param(value = "sign") Integer sign,
                                                    @Param(value = "startDate") String startDate);

    /**
     * 支付宝流水时间段流水漏抓
     */
    int findLeakageOfWater(@Param(value = "startDate") String startDate,
                           @Param(value = "endDate") String endDate);

    List<AlipayAccountOrder> selectMarginOrder(@Param(value = "created") String created);

    List<AlipayAccountOrder> selectTransferOrder(@Param(value = "created") String created);

    List<AlipayAccountOrder> selectJFBOrder(@Param(value = "created") String created);
}