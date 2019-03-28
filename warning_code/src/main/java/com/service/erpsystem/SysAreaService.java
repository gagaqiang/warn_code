package com.service.erpsystem;

import com.dao.erpsystem.SysAreaDao;
import com.ds.DS;
import com.entity.erpsystem.SysAreaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * SysArea
 */
@Service
public class SysAreaService {

    @Autowired
    private SysAreaDao dao;

    @DS(value = "erp_system")
    public SysAreaInfo getEntity(Long id) {
        return this.dao.getEntity(id);
    }

}
