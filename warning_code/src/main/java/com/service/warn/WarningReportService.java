package com.service.warn;

import com.dao.warning.*;
import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningReportDTO;
import com.utils.DateUtil;
import com.utils.web.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: hsl
 * @Date: 2018/9/5 09:45
 * @Description:
 */
@Service
public class WarningReportService {

    public final static Logger log = LoggerFactory.getLogger(WarningReportService.class);

    @Autowired
    private WarningHeaderMapper warningHeaderMapper;
    @Autowired
    private WarningGroupMapper warningGroupMapper;
    @Autowired
    private WarningDetailMapper warningDetailMapper;


    /**
     * 预警报表
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination<WarningHeaderDTO> getWarnReportPagination(WarningHeaderDTO dto, Integer pageNo, Integer pageSize) {

        //报表列表分页
        Pagination<WarningHeaderDTO> pagination = new Pagination<WarningHeaderDTO>(pageNo, pageSize);
        int rowsCount = warningHeaderMapper.findWarningHeaderCount(dto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningHeaderDTO> list = warningHeaderMapper.findWarningHeaderPagination(map);
        if (CollectionUtils.isNotEmpty(list)) {
            for (WarningHeaderDTO wh : list) {
                Integer id = wh.getWarningHeaderId();
                List<Integer> groupIds = warningGroupMapper.getGroupIdsByHeaderId(id);
                if (CollectionUtils.isNotEmpty(groupIds)) {
                    Integer mins = warningDetailMapper.getMinsCountByGroupIds(groupIds);
                    Integer countNum = warningDetailMapper.getCountDetailByGroupids(groupIds, 1, 2);
                    wh.setTimes(DateUtil.getHourAndMins(mins/countNum));
                }
            }
        }
        pagination.setCount(rowsCount);
        pagination.setData(list);
        return pagination;
    }

    //报表总汇
    public WarningReportDTO getWarnReportAll(){
        WarningReportDTO wr = warningHeaderMapper.getWarnReport();
        try {
            wr.setDays(DateUtil.getDistanceTime2(new Date(),"2018-10-09 01:00:00"));
        } catch (Exception e) {
            log.error(""+e);
        }
        return wr;
    }


    /**
     * 预警报表
     *
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> getWarnReport(WarningHeaderDTO dto, Integer pageNo, Integer pageSize) {
        Map<String, Object> resuMap = new HashMap<String, Object>();

        //报表列表分页
        Pagination<WarningHeaderDTO> pagination = this.getWarnReportPagination(dto,pageNo,pageSize);
        //报表总汇
        WarningReportDTO wr = this.getWarnReportAll();

        resuMap.put("dataReport", wr);
        resuMap.put("pagination", pagination);
        return resuMap;
    }



}
