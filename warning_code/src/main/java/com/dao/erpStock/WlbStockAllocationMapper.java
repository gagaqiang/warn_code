package com.dao.erpStock;

import com.entity.erpStock.WlbStockAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WlbStockAllocationMapper {

    List<WlbStockAllocation> select(@Param(value = "startDate") String startDate);

}