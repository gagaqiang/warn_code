package com.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisCacheConfiguration {
    Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    private  JedisPool jedisPool;
    private final ThreadLocal<Jedis> threadLocal = new ThreadLocal<Jedis>();

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;

    @Bean
    public JedisPool redisPoolFactory() {
        //logger.info("JedisPool注入成功！！");
        //logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);


        if(StringUtils.isNotBlank(password))
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
        else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, (String)null, database, (String)null);
        }

        return jedisPool;
    }


    public Jedis getJedis(){
        Jedis je = threadLocal.get();
        if(je==null){
            // 从连接池中获取
            je= jedisPool.getResource();
            threadLocal.set(je);
        }
        return je;
    }



    /**
     * 释放jedis资源
     *
     */
    public void returnResource() {
        Jedis je= threadLocal.get();
        if (je != null){
            logger.info("-----close-----"+je.hashCode());
            je.close();
            threadLocal.remove();
        }
    }


}
