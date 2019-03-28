package com.dao.warning;

import com.entity.warning.WarningJobDateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface WarningJobDateLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningJobDateLog record);

    WarningJobDateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningJobDateLog record);

    int getLogsByHeaderCode(@Param("code") String code);

}