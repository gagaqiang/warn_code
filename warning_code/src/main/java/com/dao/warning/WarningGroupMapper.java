package com.dao.warning;

import com.dto.warning.WarningGroupDTO;
import com.dto.warning.WarningGroupReportDTO;
import com.entity.warning.WarningGroup;
import com.pojo.WarningGroupPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarningGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningGroup record);

    WarningGroup selectByPrimaryKey(Integer id);

    String findWarningGroupById(Integer id);

    int updateByPrimaryKeySelective(WarningGroup record);

    List<Integer> getGroupIdsByHeaderId(@Param("headerId") Integer headerId);

    List<WarningGroupDTO> queryListByParmes(@Param("warningGroupPojo")WarningGroupPojo warningGroupPojo, @Param("pageOffset") int pageOffset, @Param("pageSize") int pageSize);

    int queryCount(@Param("warningGroupPojo")WarningGroupPojo warningGroupPojo);

    WarningGroup getWarningGroupByHeaderIdAndStatus(@Param("headerId") Integer headerId, @Param("status") Integer status);

    List<WarningGroup> getWarngroupListByHeaderId(@Param("headerId") Integer headerId);

    WarningGroupReportDTO getWarngroupReport(@Param("headerId") Integer headerId);
}