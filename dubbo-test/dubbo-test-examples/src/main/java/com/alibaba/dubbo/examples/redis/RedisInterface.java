package com.alibaba.dubbo.examples.redis;

/**
 * User: liuhanlong
 * Email: liuhanlong3304@126.com
 * Time: 16/12/1 下午10:54
 */
public interface RedisInterface {
    String get(String key);

    void set(String key, String value);

    void remove(String key);

}
