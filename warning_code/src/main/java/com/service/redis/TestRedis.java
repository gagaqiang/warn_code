package com.service.redis;

import com.config.RedisCacheConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


@Component
public class TestRedis  extends RedisCacheConfiguration {

    Logger logger = LoggerFactory.getLogger(WarnHeadRedis.class);

    private final static String WARNCODE_JEDIS_KEY_HOUR = "test:warn:hour:";

    private final static String WARNCODE_JEDIS_KEY_NUM = "test:warn:num:";


    public void getHourRedis(String code){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            logger.info("-----getHourRedis-----"+jedis.hashCode());
            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY_HOUR).append(code);
            String key = sb.toString();
            logger.info("-----getHourRedis---key--"+key);
            String value = jedis.get(key);
            logger.info("getHourRedis value ======= :" + value);
        }catch (Exception e){
            logger.error("delWarnHeaderrRedis error :"+ e.getMessage());
        }
    }


    public void addHourRedis(String value, String code){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            logger.info("-----addHourRedis-----"+jedis.hashCode());
            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY_HOUR).append(code);
            String key = sb.toString();
            if (StringUtils.isBlank(jedis.get(key))){
                jedis.del(key);
                jedis.set(key, value);
            }
        }catch (Exception e){
            logger.error("addHourRedis error :"+ e.getMessage());
        }
    }



    public void addRedis(String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            logger.info("-----addRedis-----"+jedis.hashCode());
            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY_NUM);
            String key = sb.toString();
            jedis.del(key);
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("addRedis error :"+ e.getMessage());
        }
    }

    public void getRedis(){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            logger.info("-----getRedis-----"+jedis.hashCode());
            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY_NUM);
            String key = sb.toString();
            String value = jedis.get(key);
            logger.info("getRedis value ======= :" + value);
        }catch (Exception e){
            logger.error("getRedis error :"+ e.getMessage());
        }
    }
}
