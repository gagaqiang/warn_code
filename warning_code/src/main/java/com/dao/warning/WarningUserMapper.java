package com.dao.warning;

import com.dto.warning.WarningUserDTO;
import com.entity.warning.WarningUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningUser record);

    WarningUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningUser record);

    List<WarningUserDTO> findWarningLinePagination(Map<String, Object> map);

    int findWarningLineCount(WarningUserDTO dto);

    List<WarningUserDTO> findWarningUserList(WarningUserDTO dto);

    List<WarningUser> getWarningUserByWarnHeaderId(@Param("headerId") int headerId);

    List<String> getUserIdsByHeaderId(@Param("headerId") Integer headerId);
}