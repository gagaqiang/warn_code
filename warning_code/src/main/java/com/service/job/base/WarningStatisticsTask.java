package com.service.job.base;

import com.dao.warning.WarningDetailMapper;
import com.ds.DS;
import com.dto.warning.WarningReportStatisticsDto;
import com.entity.warning.WarningGroup;
import com.entity.warning.WarningHeader;
import com.enums.WarningEnum;
import com.pojo.WarningReportStatisticsPojo;
import com.service.warn.WarningHeaderService;
import com.service.warn.WarningSendMsgService;
import com.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * 预警汇总
 */
@Component
public class WarningStatisticsTask {

    public final static Logger log = LoggerFactory.getLogger(WarningStatisticsTask.class);

    @Autowired
    private WarningDetailMapper warningDetailMapper;
    @Autowired
    private WarningSendMsgService warningSendMsgService;
    @Autowired
    private WarningHeaderService warningHeaderService;


    /**
     * 推送、统计预警数据-截止昨日】所有
     */
    @Scheduled(cron = "0 */10 * * * ?")
    @DS(value = "tc_warning")
    public void  getWarningStatisticsAndSendMsg(){
        try {
            //WarningHeader wh = getMsg(getWarningReportStatistics(0), WarningEnum.WARNING_ALL_HUIZONG.getId(), "截今0点，总计 ");
            WarningHeader wh = getMsg2(WarningEnum.WARNING_ALL_HUIZONG.getId());
            log.info(wh.getMessage());
            sendReportMsg(wh);
        }catch (ParseException e){
            log.error("error-----" + e);
        }
    }


    /**
     * 推送、统计预警数据-截止昨日】业务预警
     */
    //@Scheduled(cron = "0 */11 * * * ?")
    //@DS(value = "tc_warning")
    public void  getYWWarningStatisticsAndSendMsg(){
        try {
            WarningHeader wh = getMsg(getWarningReportStatistics(1), WarningEnum.WARNING_YW_HUIZONG.getId(), "截今0点，业务");
            log.info(wh.getMessage());
            sendReportMsg(wh);
        }catch (ParseException e){
            log.error("error-----" + e);
        }
    }


    /**
     * 推送、统计预警数据-截止昨日】 IT预警
     */
    //@Scheduled(cron = "0 */12 * * * ?")
    //@DS(value = "tc_warning")
    public void  getITWarningStatisticsAndSendMsg(){
        try {
            WarningHeader wh = getMsg(getWarningReportStatistics(2), WarningEnum.WARNING_IT_HUIZONG.getId(), "截今0点，IT");
            log.info(wh.getMessage());
            sendReportMsg(wh);
        }catch (ParseException e){
            log.error("error-----" + e);
        }
    }


    private void sendReportMsg(WarningHeader wh) throws ParseException{
        if(null != wh){
            WarningGroup wg = new WarningGroup();
            boolean flag = warningSendMsgService.isCheckTimeToSend(wh, wg);
            if (flag)
                warningSendMsgService.sendWecathMsg(wh, wg);
        }
    }

    private WarningHeader getMsg2(String warnCode){
        WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
        if(wh != null){
            WarningReportStatisticsDto it = getWarningReportStatistics(2);

            WarningReportStatisticsDto yw = getWarningReportStatistics(1);

            //截止2018-11-21号0点，业务累计8条，影响214单；IT累计9条，影响1单。
            StringBuffer sb2 = new StringBuffer();
            sb2.append("截止").append(DateUtil.getDayTime(0)).append("号0点，业务累计")
                    .append(yw.getUndoWarnNum()).append("条，影响").append(yw.getUndoAllOrders()).append("单; IT累计")
                    .append(it.getUndoWarnNum()).append("条，影响").append(it.getUndoAllOrders()).append("单。");
            wh.setMessage(sb2.toString());


        }
        return wh;
    }

    private WarningHeader getMsg(WarningReportStatisticsDto dto, String warnCode, String title){
        WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
        if(wh != null){
            /*StringBuffer sb = new StringBuffer();
            sb.append(title).append("截止").append(DateUtil.getDayTime(-1)).append(" 共发生")
                    .append(dto.getAllWarnNum()).append("条，未处理")
                    .append(dto.getUndoWarnNum()).append("条,受影响的单量")
                    .append(dto.getUndoAllOrders()).append(" 单。昨日新增")
                    .append(dto.getYesAllWarnNum()).append("条；未处理")
                    .append(dto.getYesUndoWarnNum()).append("条，受影响单量")
                    .append(dto.getYesOrderNum()).append("单。");
            wh.setMessage(sb.toString());*/


            //截今0点， IT（累X/累余X/昨增X/昨余X）条，影响（累余X/昨余X）单。

            /*StringBuffer sb1 = new StringBuffer();
            sb1.append(title).append("(累").append(dto.getAllWarnNum()).append("/累余").append(dto.getUndoWarnNum())
                    .append("/昨增").append(dto.getYesAllWarnNum()).append("/昨余").append(dto.getYesUndoWarnNum())
                    .append(") 条， 影响（累余").append(dto.getUndoAllOrders()).append("/昨余").append(dto.getYesOrderNum())
                    .append(")单。");
            wh.setMessage(sb1.toString());*/




        }

        return wh;
    }


    /**
     * 统计预警数据-截止昨日
     */
    private WarningReportStatisticsDto getWarningReportStatistics(int type){
        WarningReportStatisticsPojo po = new WarningReportStatisticsPojo();
        po.setDateTime(DateUtil.getDayTime(-1));
        po.setType(type);
        return warningDetailMapper.getWarningReportStatistics(po);
    }



}
