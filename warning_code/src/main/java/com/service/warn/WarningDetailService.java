package com.service.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.warning.*;
import com.ds.DS;
import com.dto.warning.WarningDetailDTO;
import com.dto.warning.WarningDetailListDTO;
import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningUserDTO;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.WarningDetail;
import com.entity.warning.WarningGroup;
import com.entity.warning.WarningHeader;
import com.entity.warning.WarningUser;
import com.utils.DateUtil;
import com.utils.web.Pagination;
import com.utils.WarnOnceCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Auther: hsl
 * @Date: 2018/9/6 16:03
 * @Description:
 */
@Service
public class WarningDetailService {

    public final static Logger log = LoggerFactory.getLogger(WarningDetailService.class);

    @Autowired
    private WarningDetailMapper warningDetailMapper;
    @Autowired
    private WarningShopMapper warningShopMapper;
    @Autowired
    private WarningUserMapper warningUserMapper;
    @Autowired
    private WarningGroupMapper warningGroupMapper;
    @Autowired
    private WarningHeaderService warningHeaderService;
    @Autowired
    private WarningOperLogService warningOperLogService;


    public List<WarningDetailDTO> getDetailList(WarningDetailDTO dto, Integer pageNo, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningDetailDTO> list = warningDetailMapper.findWarningDetailPagination(map);

        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                WarningDetailDTO warningDetailDTO = list.get(i);
                //shopId为空
                if (warningDetailDTO.getShopId() == null) {
                    WarningHeaderDTO warningHeaderDTO = warningShopMapper.findUsersByGroupId(warningDetailDTO.getGroupId());
                    if (warningHeaderDTO != null) {
                        warningDetailDTO.setUsers(warningHeaderDTO.getUsers());
                    }
                } else {
                    //shopId不为空
                    List<String> warningUserDTOList = warningShopMapper.findUsersByGroupIdAndShopId(warningDetailDTO.getGroupId(), warningDetailDTO.getShopId());
                    if (org.apache.commons.collections.CollectionUtils.isNotEmpty(warningUserDTOList)) {
                        String names = JSONObject.toJSONString(warningUserDTOList).replace("\"", "").replace("[", "").replace("]", "");
                        warningDetailDTO.setUsers(names);
                    } else {
                        WarningHeaderDTO warningHeaderDTO = warningShopMapper.findUsersByGroupId(warningDetailDTO.getGroupId());
                        if (warningHeaderDTO != null) {
                            warningDetailDTO.setUsers(warningHeaderDTO.getUsers());
                        }
                    }
                }
            }
        }
        return list;
    }

    //获取预警明细订单列表
    public Pagination<WarningDetailDTO> findWarningDetailPagination(WarningDetailDTO dto, Integer pageNo, Integer pageSize) throws Exception {
        Pagination<WarningDetailDTO> pagination = new Pagination<WarningDetailDTO>(pageNo, pageSize);
        int rowsCount = warningDetailMapper.findWarningDetailCount(dto);
        pagination.setCount(rowsCount);
        pagination.setData(getDetailList(dto, pageNo, pageSize));
        return pagination;
    }

    //批量关闭
    @Transactional
    public List<Map<String, Object>> updateCloseAll(WarningDetailListDTO dtoList) throws Exception {

        List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
        List<WarningDetailDTO> list = dtoList.getWarningDetailDTOList();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> m = updateClose(list.get(i));
            mList.add(m);
        }
        return mList;
    }


    //关闭
    @Transactional
    public Map<String, Object> updateClose(WarningDetailDTO dto) {

        //更新预警项的当前影响订单数
        String code = warningGroupMapper.findWarningGroupById(dto.getId());
        WarningHeader warningHeader = warningHeaderService.getWarningHeaderByCode(code);
        Map<String, Object> m = new HashMap<String, Object>();

        if (null != warningHeader) {

            WarningDetail warningDetail = warningDetailMapper.selectById(dto.getId());

            if(null != warningDetail){
                warningDetail.setId(dto.getId());
                warningDetail.setIsClose(2);
                warningDetail.setStatus(1);
                warningDetail.setDelTime(new Date());//处理完成时间

                Long allMin = DateUtil.getDatePoor(new Date(), warningDetail.getCreateTime());
                warningDetail.setMins(allMin.intValue());

                String allTime = DateUtil.getHourAndMins(allMin.intValue());
                warningDetail.setTimeConsuming(allTime);
                warningDetail.setAttribuate1(dto.getOperUser());

                warningDetailMapper.updateByPrimaryKeySelective(warningDetail);


                int UnProcessedNum = warningHeader.getUnProcessedNum();
                int ProcessedNum = warningHeader.getProcessedNum();
                int CurrentOrderNumber = warningHeader.getCurrentOrderNumber();
                WarningGroup warningGroup = warningGroupMapper.selectByPrimaryKey(warningDetail.getGroupId());

                //处理只有一个数据
                if (WarnOnceCodeUtil.ONLY_ONCE_CODE.contains(code)) {
                    warningGroup.setTimeConsuming(allTime);
                    warningGroup.setDelTime(new Date());
                    warningGroup.setStatus(1);
                    warningGroupMapper.updateByPrimaryKeySelective(warningGroup);

                    UnProcessedNum = UnProcessedNum - 1;
                    ProcessedNum = ProcessedNum + 1;

                } else {
                    //未处理完的订单
                    List<WarningDetail> unDealDetailList = warningDetailMapper.getWarningDetailDto(warningDetail.getGroupId(), 2, null);
                    //已完成
                    List<WarningDetail> dealList = warningDetailMapper.getWarningDetailDto(warningDetail.getGroupId(), 1, null);

                    //当前为最后一条
                    if (CollectionUtils.isEmpty(unDealDetailList)) {
                        int Min = 0;
                        int averageMin = 0;
                        int num = 0;
                        if (!CollectionUtils.isEmpty(dealList)) {
                            for (WarningDetail detail : dealList) {
                                Min = Min + detail.getMins();
                                num = num + 1;
                            }
                        }
                        Min = allMin.intValue() + Min;
                        averageMin = Min / num;

                        warningGroup.setTimeConsuming(DateUtil.getHourAndMins(averageMin));
                        warningGroup.setDelTime(new Date());
                        warningGroup.setStatus(1);
                        warningGroupMapper.updateByPrimaryKeySelective(warningGroup);

                        UnProcessedNum = UnProcessedNum - 1;
                        ProcessedNum = ProcessedNum + 1;
                    }

                    m.put("code", code);
                    m.put("platformCode", warningDetail.getPlatfromCode());
                }

                warningHeader.setCurrentOrderNumber(CurrentOrderNumber - 1 > 0 ? CurrentOrderNumber - 1 : 0);
                warningHeader.setProcessedNum(ProcessedNum);
                warningHeader.setUnProcessedNum(UnProcessedNum > 0 ? UnProcessedNum : 0);
                warningHeader.setLastTime(new Date());
                warningHeaderService.updateWarnHeader(warningHeader);


                warningOperLogService.insertOperLog(dto.getOperUser(), "关闭预警详情"
                        , warningDetail.getPlatfromCode(), warningHeader.getId(), String.valueOf(warningDetail.getId()));
            }
        } else {
            m.put("error", "当前预警项预警关闭");
        }
        return m;

    }


    @DS(value = "tc_warning")
    public List<ErrorMsg> getTcInWarningMsg(String warnCode) {
        return warningDetailMapper.getTcInWarningMsg(warnCode);
    }


    @DS(value = "tc_warning")
    public List<ErrorMsg> getUnDoTcInWarningMsg(String warnCode, List<String> codes) {
        return warningDetailMapper.getUnDoTcInWarningMsg(warnCode, codes);
    }

    @DS(value = "tc_warning")
    public List<WarningDetailDTO> getDetailIdByDetailCodeAndPlatfromCode(String detailCode, String platfromCode) {
        return warningDetailMapper.getDetailIdByDetailCodeAndPlatfromCode(detailCode, platfromCode);
    }

    @DS(value = "tc_warning")
    public Integer getDetailIdByPlatfromCodeAndCode(String code, String platfromCode) {
        return warningDetailMapper.getDetailIdByPlatfromCodeAndCode(code, platfromCode);
    }

}
