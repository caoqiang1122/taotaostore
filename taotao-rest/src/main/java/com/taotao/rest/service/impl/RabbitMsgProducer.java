package com.taotao.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/1/24 9:29
 * @Version 1.0
 */
@Service
public class RabbitMsgProducer {
    private final Logger LOG = LoggerFactory.getLogger(RabbitMsgProducer.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMesssage(Object message) throws IOException{
//        LOG.info("to send message:{}", message);
        System.out.println("to send message:{}" + message);
        amqpTemplate.convertAndSend("queueTestKey", message);
        amqpTemplate.convertAndSend("queueTestChris", message);
    }
}
