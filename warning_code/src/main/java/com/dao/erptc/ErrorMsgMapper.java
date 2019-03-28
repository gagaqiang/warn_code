package com.dao.erptc;

import com.entity.erptc.ErrorMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErrorMsgMapper {

    List<ErrorMsg> getErrorMsgByParmes(@Param("code") String code, @Param("isDeal") Integer isDeal
                                        ,@Param("startDate") String startDate);

    int updateIsDeal(@Param(value = "code") String code, @Param("platformCode") String platformCode);
}
