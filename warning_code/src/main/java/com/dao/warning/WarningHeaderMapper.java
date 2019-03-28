package com.dao.warning;

import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningReportDTO;
import com.entity.warning.WarningHeader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningHeaderMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningHeader record);

    WarningHeader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningHeader record);

    WarningHeader getEntityById(Integer id);

    List<WarningHeaderDTO> findWarningHeaderPagination(Map<String, Object> map);

    int findWarningHeaderCount(WarningHeaderDTO dto);

    WarningReportDTO getWarnReport();

    WarningHeader getWarningHeaderByCode(@Param("code") String code);

    List<WarningHeaderDTO> getAllCodeList();

    List<WarningHeader> getAllWarning();
}