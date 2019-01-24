package com.taotao.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RabbitMsgProducerTest {
    private Logger logger = LoggerFactory.getLogger(RabbitMsgProducerTest.class);

    private ApplicationContext context = null;

    @BeforeTest
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-service.xml");
    }

    @Test
    public void sendMesg() throws Exception{
        RabbitMsgProducer messageProducer = (RabbitMsgProducer) context.getBean("rabbitMsgProducer");
        int a = 10;
        while (a > 0) {
            messageProducer.sendMesssage("Hello, I am amq sender num :" + a--);
            try {
                //暂停一下，好让消息消费者去取消息打印出来
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}