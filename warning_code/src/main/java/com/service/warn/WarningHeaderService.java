package com.service.warn;

import com.dao.warning.*;
import com.ds.DS;
import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningHeaderListDTO;
import com.dto.warning.WarningUserDTO;
import com.entity.warning.WarningHeader;
import com.entity.warning.WarningUser;
import com.entity.warning.WarningUserShop;
import com.service.redis.WarnHeadRedis;
import com.utils.web.Pagination;
import gy.lib.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WarningHeaderService {

    public final static Logger log = LoggerFactory.getLogger(WarningHeaderService.class);

    @Autowired
    private WarningHeaderMapper warningHeaderMapper;
    @Autowired
    private WarningUserMapper warningUserMapper;
    @Autowired
    private WarningUserShopMapper warningUserShopMapper;
    @Autowired
    private WarnHeadRedis warnHeadRedis;
    @Autowired
    private WarningOperLogService warningOperLogService;

    @DS(value = "tc_warning")
    public void updateWarnHeader(WarningHeader record){
        warningHeaderMapper.updateByPrimaryKeySelective(record);
        warnHeadRedis.addWarnHeaderRedis(record);
    }


    @DS(value = "tc_warning")
    public WarningHeader getWarningHeaderByCode(String warnCode){
        WarningHeader wh = null;
        try {
            wh = warnHeadRedis.getWarningHeaderByCode(warnCode);
            if(null == wh){
                wh = warningHeaderMapper.getWarningHeaderByCode(warnCode);
                if(null != wh ){
                    warnHeadRedis.addWarnHeaderRedis(wh);
                }
            }
        }catch (Exception e){
            log.error("获取预警项头信息失败!!!"+ e.getMessage());
        }
        return wh;
    }


    //获取预警设置列表
    public Pagination<WarningHeaderDTO> findWarningHeaderPagination(WarningHeaderDTO dto, Integer pageNo, Integer pageSize) throws Exception {
        Pagination<WarningHeaderDTO> pagination = new Pagination<WarningHeaderDTO>(pageNo, pageSize);
        int rowsCount = warningHeaderMapper.findWarningHeaderCount(dto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningHeaderDTO> list = warningHeaderMapper.findWarningHeaderPagination(map);
        pagination.setCount(rowsCount);
        pagination.setData(list);
        return pagination;
    }

    //保存预警设置(预警项数据)
    @Transactional
    public Integer addWarningHeader(WarningHeaderDTO dto) throws Exception {
        Integer id = null;
        try {
            WarningHeader record = new WarningHeader();
            BeanUtils.copyProperties(dto, record);
            record.setLastTime(new Date());


            if (dto.getWarningHeaderId() == null) {
                record.setCreateTime(new Date());
                record.setStatus(1);//默认进行中
                //log.info("新增==>>" + JSON.toJSONString(record));

                warningHeaderMapper.insertSelective(record);
                id = record.getId();

                //记录日志
                warningOperLogService.insertOperLog(dto.getOperUser(), "新增预警项"
                        , (String) null, id, null);

            } else {
                record.setId(dto.getWarningHeaderId());
                warningHeaderMapper.updateByPrimaryKeySelective(record);
                //log.info("修改==>>" + JSON.toJSONString(record));

                id = dto.getWarningHeaderId();
                //删除缓存
                warnHeadRedis.delWarnHeaderrRedis(record.getCode());

                //记录日志
                warningOperLogService.insertOperLog(dto.getOperUser(), "修改预警项"
                        , (String) null, id, null);

            }
            addWarningLine(dto,id);
        } catch (Exception e) {
            throw new Exception("保存预警设置异常："+e.getMessage());
        }
        return id;
    }

    //保存预警设置(业务员+店铺数据)
    public void addWarningLine(WarningHeaderDTO dto,Integer warningHeaderId) throws Exception {
        //log.info("warningHeaderId==>>" + JSON.toJSONString(warningHeaderId));
        //log.info("WarningHeaderDTO==>>" + JSON.toJSONString(dto));

        StringBuffer sb = new StringBuffer();
        List<WarningUserDTO> warningUserDTOList = dto.getWarningUserDTOList();
        if (warningUserDTOList != null && !warningUserDTOList.isEmpty()) {
            for (int i = 0; i < warningUserDTOList.size(); i++) {
                WarningUserDTO warningUserDTO = warningUserDTOList.get(i);

                if (i == 0) {
                    sb.append(warningUserDTO.getUserName());
                } else {
                    if( !sb.toString().contains(warningUserDTO.getUserName())){
                        sb.append("," + warningUserDTO.getUserName());
                    }
                }
                //log.info("warningUserDTO.getWechatUserId()==>>" + JSON.toJSONString(warningUserDTO.getWechatUserId()));

                warningUserDTO.setUserId(warningUserDTO.getWechatUserId());
                warningUserDTO.setName(warningUserDTO.getUserName());
                warningUserDTO.setLastTime(new Date());

                WarningUser warningUser = new WarningUser();
                BeanUtils.copyProperties(warningUserDTO, warningUser);

                WarningUserShop warningUserShop = new WarningUserShop();
                BeanUtils.copyProperties(warningUserDTO, warningUserShop);

                if (!StringUtil.isEmpty(warningUserDTO.getNewFlag()) && "Y".equals(warningUserDTO.getNewFlag())) {
                    warningUser.setWarningHeaderId(warningHeaderId);
                    warningUser.setCreateTime(new Date());
                    warningUserMapper.insertSelective(warningUser);
                    Integer id = warningUser.getId();

                    warningUserShop.setUserId(id);
                    warningUserShop.setWarningHeaderId(warningHeaderId);
                    warningUserShop.setCreateDate(new Date());
                    warningUserShopMapper.insertSelective(warningUserShop);
                } else {
                    warningUser.setId(Integer.parseInt(warningUserDTO.getUserId()));
                    //log.info("修改warningUser==>>" + JSON.toJSONString(warningUser));

                    warningUserMapper.updateByPrimaryKeySelective(warningUser);

                    warningUserShop.setId(warningUserDTO.getId());
                    //log.info("修改warningUserShop==>>" + JSON.toJSONString(warningUserShop));

                    warningUserShopMapper.updateByPrimaryKeySelective(warningUserShop);
                }
            }
            WarningHeader record = new WarningHeader();
            record.setId(warningHeaderId);
            record.setUsers(sb.toString());
            warningHeaderMapper.updateByPrimaryKeySelective(record);
        }
    }


    //设置启用、停用
    @Transactional
    public void updataWarningStatus(WarningHeaderListDTO warningHeaderListDTO) {
        //log.info("warningHeaderListDTO==>>" + JSON.toJSONString(warningHeaderListDTO));
        List<WarningHeaderDTO> warningHeaderDTOList = warningHeaderListDTO.getWarningHeaderDTOList();
        if (warningHeaderDTOList != null && !warningHeaderDTOList.isEmpty()) {
            for (int i = 0; i < warningHeaderDTOList.size(); i++) {
                WarningHeaderDTO dto = warningHeaderDTOList.get(i);
                WarningHeader warningHeader = new WarningHeader();
                warningHeader.setId(dto.getWarningHeaderId());
                warningHeader.setStatus(dto.getStatus());
                warningHeader.setVersion(dto.getVersion());
                warningHeader.setLastTime(new Date());
                //log.info("warningHeader==>>" + JSON.toJSONString(warningHeader));
                warningHeaderMapper.updateByPrimaryKeySelective(warningHeader);


                //删除缓存
                warnHeadRedis.delWarnHeaderrRedis(dto.getCode());

                String operTypeName = dto.getStatus() == 1?"启用":"停用";
                //记录日志
                warningOperLogService.insertOperLog(dto.getOperUser(), operTypeName, (String) null, dto.getWarningHeaderId(), null);

            }
        }
    }

}
