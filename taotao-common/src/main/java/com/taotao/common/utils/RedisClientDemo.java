package com.taotao.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientDemo {

    //单机版测试
    public void testRedisSingle() throws Exception{
        //创建一个jedis对象
        Jedis jedis = new Jedis("192.168.192.130",6379);
        jedis.set("name","曹强");
        jedis.set("age","25");
        String string = jedis.get("name");
        System.out.println(string);
        System.out.println(jedis.get("age"));

        jedis.close();
    }

    //使用连接池
    public void testRedisPool() throws Exception{
        //创建一个连接池对象
        JedisPool jedisPool = new JedisPool("192.168.192.130",6379);

        //从连接池中获取一个连接
        Jedis jedis = jedisPool.getResource();

        jedis.set("name1","曹强");
        jedis.set("age1","25");
        String string = jedis.get("name1");
        System.out.println(string);
        System.out.println(jedis.get("age1"));

        //jedis必须关闭
        jedis.close();

        //系统关闭时关闭连接池
        jedisPool.close();

    }

}
