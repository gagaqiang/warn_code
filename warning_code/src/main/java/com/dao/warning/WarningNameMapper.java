package com.dao.warning;

import com.dto.warning.WarningHeaderDTO;
import com.entity.warning.WarningName;

import java.util.List;
import java.util.Map;

public interface WarningNameMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningName record);

    WarningName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningName record);

    List<WarningHeaderDTO> findWarningNamePagination(Map<String, Object> map);

    int findWarningNameCount(WarningHeaderDTO dto);
}