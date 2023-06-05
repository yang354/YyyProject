package com.yyy.common.redis.annotation;

import com.yyy.common.core.constant.CacheConstants;
import com.yyy.common.core.enums.LimitType;

import java.lang.annotation.*;


/**
 * 限流注解
 * 
 * @author yyyz
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter
{
    /**
     * 限流key
     */
    public String key() default CacheConstants.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒  单位时间内超过默认访问次数，触发限流10秒钟，10秒钟后正常访问
     */
    public int time() default 10;

    /**
     * 限流次数   单位时间内能访问多少次，默认10次
     */
    public int count() default 10;

    /**
     * 限流类型
     */
//    public LimitType limitType() default LimitType.DEFAULT;
    public LimitType limitType() default LimitType.IP;
}
