package com.dao.sys;

import com.entity.TenantInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.sql.DataSourceDefinition;
import java.util.List;


/**
 * Tenant
 */
public interface TenantDao{


    public void create(TenantInfo entity);

    public void update(TenantInfo entity);

    public void audit(TenantInfo entity);

    public TenantInfo getEntity(Long id);

    void deleteById(Integer id);

    public TenantInfo getInfoByCode(@Param(value = "tenantCode") String tenantCode);

    public TenantInfo getInfoByName(@Param(value = "tenantName") String tenantName);

    public List<TenantInfo> getListByName(@Param(value = "name") String tenantName);

}
