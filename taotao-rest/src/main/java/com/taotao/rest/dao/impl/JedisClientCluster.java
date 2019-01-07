package com.taotao.rest.dao.impl;

import com.taotao.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @Title redis客户端集群版实现类
 * @Description: redis客户端集群版实现类
 * @Author caoqiang
 * @Date 2019/1/7 15:42
 * @Version 1.0
 */
public class JedisClientCluster implements JedisClient {
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        String string = jedisCluster.get(key);
        return string;
    }

    @Override
    public String set(String key, String value) {
        String string = jedisCluster.set(key, value);
        return string;
    }

    @Override
    public long incr(String key) {
        Long result = jedisCluster.incr(key);
        return result;
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Long result = jedisCluster.hset(hkey, key, value);
        return result;
    }

    @Override
    public String hget(String hkey, String key) {
        String string = jedisCluster.hget(hkey, key);
        return string;
    }

    @Override
    public Long del(String key) {
        Long result = jedisCluster.del(key);
        return result;
    }

    @Override
    public Long hdel(String hkey, String key) {
        Long result = jedisCluster.hdel(hkey, key);
        return result;
    }

    @Override
    public Long expire(String key, int second) {
        Long result = jedisCluster.expire(key, second);
        return result;
    }
}
