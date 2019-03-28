package com.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JedisPoolAspect {
    @Pointcut("execution(* com.service.redis.*.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 释放jedis资源
     *
     * @param point
     */
    @After(value = "pointCutMethod()")
    public void returnResource(JoinPoint point) {
        if (point != null && point.getTarget() instanceof RedisCacheConfiguration) {
            RedisCacheConfiguration redis = (RedisCacheConfiguration) point.getTarget();
            redis.returnResource();
        }
    }


}
