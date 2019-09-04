package com.taotao.common.utils;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;


public class RedisClientDemo {

    public static void main(String[] args) {

        try {
//            testRedisSingle();
            testRedisCluster();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //单机版测试

    public static void testRedisSingle() throws Exception{
        //创建一个jedis对象
        Jedis jedis = new Jedis("10.73.242.247",7001);
//        jedis.set("name","曹强");
//        jedis.set("age","25");
        String string = jedis.get("name");
        System.out.println(string);
        System.out.println(jedis.get("age"));

        jedis.close();
    }

    public static void testRedisCluster() throws Exception{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMinIdle(20);
        config.setMaxWaitMillis(6 * 1000);
        config.setTestOnBorrow(true);


        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("10.73.242.247", 7001));
        jedisClusterNodes.add(new HostAndPort("10.73.242.247", 7002));
        jedisClusterNodes.add(new HostAndPort("10.73.242.247", 7003));
        jedisClusterNodes.add(new HostAndPort("10.73.242.247", 7004));
        jedisClusterNodes.add(new HostAndPort("10.73.242.247", 7005));
        jedisClusterNodes.add(new HostAndPort("10.73.242.247", 7006));
        //创建一个jedis对象
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100,config);

        jedisCluster.set("name","曹强");
        jedisCluster.set("age","25");
        String string = jedisCluster.get("name");
        System.out.println(string);
        System.out.println(jedisCluster.get("age"));

        jedisCluster.close();
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
