package com.service.job.base;

import com.alibaba.fastjson.JSONObject;
import com.ds.DS;
import com.service.warn.WarningGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WarningHeaderTask {

    public final static Logger _logger = LoggerFactory.getLogger(WarningHeaderTask.class);

    @Autowired
    private WarningGroupService warningGroupService;

    @Scheduled(cron = "0 0 1,5,9,13,17,22 * * ?")
    @DS(value = "tc_warning")
    public void getUserErrorMsg(){
        _logger.info("WarningHeaderTask ---------");
        try {
            warningGroupService.dealWarnReport();
        }catch (Exception e){
            _logger.error(JSONObject.toJSONString(e));
        }
    }
}
