package com.dao.warning;

import com.entity.warning.WarningSendMessageLog;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface WarningSendMessageLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningSendMessageLog record);

    WarningSendMessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningSendMessageLog record);

    WarningSendMessageLog getLastSendMeaasgeLogByGroupId(@Param("groupId")Integer groupId, @Param("warningHeaderId")Integer warningHeaderId);
}