package com.dao.warning;

import com.dto.warning.WarningDetailDTO;
import com.dto.warning.WarningGroupDTO;
import com.dto.warning.WarningReportStatisticsDto;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.WarningDetail;
import com.pojo.WarningReportStatisticsPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(WarningDetail record);

    WarningDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningDetail record);

    Integer getMinsCountByGroupIds(@Param(value = "groupIds")List<Integer> groupIds);

    WarningGroupDTO getNumByGroupId(@Param("groupId") int groupId);

    List<WarningDetailDTO> findWarningDetailPagination(Map<String, Object> map);

    int findWarningDetailCount(WarningDetailDTO dto);

    List<WarningDetail> getWarningDetailDto(@Param("groupId") int groupId, @Param("status") int status, @Param("platfromCode") String platfromCode);

    int getSumMinsById(@Param("groupId") Integer groupId);

    //更新未完成的发送时间
    void updateSendTimeByGroupId(@Param("groupId") Integer groupId);

    int getSumOrderNumByStatusAndClosed(@Param("groupId") int groupId, @Param("status") int status, @Param("isClose") int isClose);
    //获取集合的数量已完成
    Integer getCountDetailByGroupids(@Param(value = "groupIds")List<Integer> groupIds, @Param("status") int status, @Param("isClose") int isClose);

    //统计预警数据-截止昨日
    WarningReportStatisticsDto getWarningReportStatistics(WarningReportStatisticsPojo warningReportStatisticsPojo);

    //获取未处理的单子
    List<ErrorMsg> getTcInWarningMsg(@Param("warnCode") String warnCode);

    //获取分当前集合的数据
    List<ErrorMsg> getUnDoTcInWarningMsg(@Param("warnCode") String warnCode, @Param("codeList") List<String> codeList);

    List<WarningDetailDTO> getDetailIdByDetailCodeAndPlatfromCode(@Param("detailCode") String detailCode,@Param("platfromCode") String platfromCode);

    WarningDetail selectById(@Param("id") Integer id);

    Integer getDetailIdByPlatfromCodeAndCode(@Param("code") String code,@Param("platfromCode") String platfromCode);

}