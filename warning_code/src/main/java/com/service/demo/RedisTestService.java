package com.service.demo;

import com.entity.warning.WarningHeader;
import com.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisTestService {

    private final static String TEST_JEDIS_KEY = "aux:warn:jedis:test:frist:";

    private final static String TEST_JEDIS_KEY_CODE = "aux:warn:jedis:test:frist:code:";

    @Autowired
    private JedisPool jedisPool;

    public void setJedisVal(String val){
        Jedis jedis = jedisPool.getResource();
        jedis.set(TEST_JEDIS_KEY, val);
    }

    public String getVal(){
        Jedis jedis = jedisPool.getResource();
        return jedis.get(TEST_JEDIS_KEY);
    }

    public static void getMes(String title){
        String tem = "这是一个{title}的日子！！";
        String m = tem.replace("{title}", title);
        System.out.println(m);

    }


    public void setSerializeObjectTest(){

        WarningHeader wh = new WarningHeader();
        wh.setCode("ABCD阿卡卡姐");

        String key = TEST_JEDIS_KEY_CODE+"123";
        Jedis jedis = jedisPool.getResource();
        jedis.setex(key.getBytes(), 60 , SerializeUtil.serialize(wh));
    }

    public void getSerializeObjectTest(){
        String key = TEST_JEDIS_KEY_CODE+"123";
        Jedis jedis = jedisPool.getResource();

        WarningHeader wh = (WarningHeader) SerializeUtil.unserialize(jedis.get(key.getBytes()));

        System.out.println(wh.getCode());
    }
}
