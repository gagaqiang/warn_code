package com.dao.warning;

import com.dto.warning.WarningUserDTO;
import com.entity.warning.WarningUserShop;

import java.util.List;

public interface WarningUserShopMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningUserShop record);

    WarningUserShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningUserShop record);

    List<WarningUserShop> findUserShopByWarningHeaderIdAndUserId(WarningUserDTO dto);

}