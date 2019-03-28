package com.service.job;

import com.ds.DS;
import com.enums.WarningEnum;
import com.service.job.order.SapErrorJobTask;
import com.service.redis.TestRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestRedisJob {

    public final static Logger _logger = LoggerFactory.getLogger(TestRedisJob.class);


    @Autowired
    private TestRedis testRedis;

    //@Scheduled(cron = "0 */1 * * * ?")
    public void testaddRedis(){
        try {
            testRedis.addRedis("test00 - " + System.currentTimeMillis());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH");
            String code = sdf.format(new Date());

            testRedis.addHourRedis("hour: - " +System.currentTimeMillis(), code);
        }catch (Exception e){
            _logger.error(e.getMessage());
        }
    }

    //@Scheduled(cron = "0 */2 * * * ?")
    public void testgetRedis(){
        try {
            testRedis.getRedis();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH");
            String code = sdf.format(new Date());
            testRedis.getHourRedis(code);
        }catch (Exception e){
            _logger.error(e.getMessage());
        }
    }

}
