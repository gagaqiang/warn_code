package com.dao.erptc;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointHeaderMapper {

    Integer listPointOrderByCreateDate(@Param(value = "stateDate") String stateDate, @Param(value = "endDate") String endDate);

    Integer getCountByMonth(@Param(value = "monthParme") String monthParme);
}
