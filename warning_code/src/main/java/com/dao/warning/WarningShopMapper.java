package com.dao.warning;

import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningUserDTO;
import com.entity.warning.WarningShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningShopMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningShop record);

    WarningShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningShop record);

    List<WarningUserDTO> findWarningShopPagination(Map<String, Object> map);

    int findWarningShopCount(WarningUserDTO dto);

    List<WarningShop> findWarningShopByShopIdAndType(WarningUserDTO dto);

    WarningHeaderDTO findUsersByGroupId(@Param("groupId") Integer groupId);

    List<String> findUsersByGroupIdAndShopId(@Param("groupId") Integer groupId,@Param("shopId") Integer shopId);

}