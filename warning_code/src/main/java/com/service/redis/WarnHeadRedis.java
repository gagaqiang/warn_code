package com.service.redis;

import com.config.RedisCacheConfiguration;
import com.entity.warning.WarningHeader;
import com.utils.SerializeUtil;
import gy.lib.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


@Component
public class WarnHeadRedis extends RedisCacheConfiguration{

    Logger logger = LoggerFactory.getLogger(WarnHeadRedis.class);

    private final static String WARNCODE_JEDIS_KEY = "aux:warn:code:";

    public void delWarnHeaderrRedis(String code){
        Jedis jedis = null;
        try {
            jedis = redisPoolFactory().getResource();
            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY).append(code);
            String key = sb.toString();
            jedis.del(key.getBytes());
        }catch (Exception e){
            logger.error("delWarnHeaderrRedis error :"+ e);
        }finally {
            if (null != jedis)
                //logger.info("close redis delWarnHeaderrRedis");
            jedis.close();
        }

    }


    public void addWarnHeaderRedis(WarningHeader record){
        Jedis jedis = null;
        try {
            jedis = redisPoolFactory().getResource();
            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY).append(record.getCode());
            String key = sb.toString();
            jedis.del(key.getBytes());

            jedis.setex(key.getBytes(), 60 * 60 * 24, SerializeUtil.serialize(record));
        }catch (Exception e){
            logger.error("addWarnHeaderRedis error :"+ e);
        }finally {
            if (null != jedis)
                //logger.info("close redis addWarnHeaderRedis");
            jedis.close();
        }
    }

    /**
     * 向redis里存入数据
     *
     * @param key
     * @param value
     */
    public void setValue(String key, String value)  {
        Jedis jedis = null;
        try {
            jedis = redisPoolFactory().getResource();
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("addWarnHeaderRedis error :"+ e);
        }finally {
            if (null != jedis)
                //logger.info("close redis addWarnHeaderRedis");
                jedis.close();
        }
    }

    /**
     * 获取对 像
     *
     * @param key
     * @return
     */
    public String getObjectStr(String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolFactory().getResource();
            String object = jedis.get(key);
            return object;
        }finally {
            if (null != jedis)
                //logger.info("close redis addWarnHeaderRedis");
                jedis.close();
        }
    }


    public WarningHeader getWarningHeaderByCode(String warnCode) {
        WarningHeader wh = null;
        Jedis jedis = null;
        try {
            jedis = redisPoolFactory().getResource();
            //jedis = getJedis();
            //logger.info("++++++++++++++++++"+jedis.hashCode());

            StringBuffer sb = new StringBuffer();
            sb.append(WARNCODE_JEDIS_KEY).append(warnCode);
            String key = sb.toString();
            byte[] bytes = jedis.get(key.getBytes());
            if(null != bytes){
                wh = (WarningHeader) SerializeUtil.unserialize(jedis.get(key.getBytes()));
            }
        }catch (Exception e){
            logger.error("获取预警项头信息缓存失败!!!"+ e);
        }finally {
            if (null != jedis) {
                //logger.info("close redis getWarningHeaderByCode");
            }
            jedis.close();
        }
        return wh;
    }

    /**
     * 获取自增加后的值
     *
     * @param key
     * @return
     */
    public  Long getIncrement(String key) {
        Jedis jedis = null;
        Long increment = 0l;
        try {
            if (StringUtil.isEmpty(key)) {
                throw new Exception("redis 获取自增长key 为空");
            }

            jedis = redisPoolFactory().getResource();
            jedis.select(2);
            increment = jedis.incr(key);
            if (increment == null) {
                throw new Exception("redis 获取自增长结果为空");
            }
            logger.info("redis getIncrement success ...");
        }catch (Exception e){
            logger.error("getIncrement error!!!"+ e);
        }finally {
            if (null != jedis)
                logger.info("close redis getIncrement");
            jedis.close();
        }
        return increment;
    }
}
