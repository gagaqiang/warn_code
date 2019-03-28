package com.dao.warning;

import com.dto.warning.WarningWechatUserDTO;
import com.entity.warning.WarningWechatUser;

import java.util.List;
import java.util.Map;

public interface WarningWechatUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningWechatUser record);

    WarningWechatUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningWechatUser record);

    List<WarningWechatUserDTO> findWechatUserPagination(Map<String, Object> map);

    int findWechatUserCount(WarningWechatUserDTO dto);

}