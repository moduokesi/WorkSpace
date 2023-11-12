package com.treat.utils;

/**
 * redis相关常量
 */
public class RedisContants {

    public static final String LOGIN_TOKEN_KEY = "login:code:";
    public static final Long LOGIN_TOKEN_TTL = 300L;
    public static final Long CACHE_NULL_TTL = 2L;
    public static final String LOCK_KEY = "lock:";
}
