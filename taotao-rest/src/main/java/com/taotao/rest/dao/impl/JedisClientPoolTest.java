package com.taotao.rest.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JedisClientPoolTest {


    @Test
    public void get() {
    }

    @Test
    public void set() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisClientPool jedisClientPool = applicationContext.getBean(JedisClientPool.class);
        jedisClientPool.set("1","23");
        System.out.println(jedisClientPool.get("1"));
    }
}