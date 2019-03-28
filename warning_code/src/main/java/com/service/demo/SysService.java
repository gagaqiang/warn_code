package com.service.demo;

import com.alibaba.fastjson.JSONObject;
import com.dao.warning.WarningHeaderMapper;
import com.ds.DS;
import com.dao.sys.TenantDao;
import com.entity.TenantInfo;
import com.entity.erpsystem.SysAreaInfo;
import com.entity.warning.WarningHeader;
import com.service.erpsystem.SysAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysService {
    public final static Logger _logger = LoggerFactory.getLogger(SysService.class);

    @Autowired
    private TenantDao tenDao;
    @Autowired
    private WarningHeaderMapper warningHeaderMapper;

    @Autowired
    private SysAreaService sysAreaService;

    @DS(value = "tc_warning")
    public void test(){
        WarningHeader wj = warningHeaderMapper.getEntityById(1);
        _logger.info(wj.getName());
        SysAreaInfo sysAreaInfo = sysAreaService.getEntity(1l);
        _logger.info(sysAreaInfo.getName());
    }



    @DS(value = "system_aux")
    public TenantInfo getEntity(Long id){
        TenantInfo ti = tenDao.getEntity(id);
        return ti;
    }

    @DS(value = "system_aux")
    public String getName(Long id){
        TenantInfo ti = tenDao.getEntity(id);
        _logger.info("----" + JSONObject.toJSONString(ti));
        return ti.getName();
    }

    @Transactional
    @DS(value = "system_aux")
    public void add(){
        TenantInfo ti = new TenantInfo();
        ti.setCode("abcd");
        ti.setName("测试一下");
        ti.setStatus(1);
        tenDao.create(ti);
    }

    @Transactional
    @DS(value = "system_aux")
    public void testAdd(){
        add();
        int i = 10;
        int k = 0;
        int m =  i / k;
    }


    @Transactional
    @DS(value = "system_aux")
    public void testAddEntity(TenantInfo ti){
        tenDao.create(ti);
    }

    @DS(value = "system_aux")
    @Transactional
    public void del(Integer id){
        tenDao.deleteById(id);
    }

}
