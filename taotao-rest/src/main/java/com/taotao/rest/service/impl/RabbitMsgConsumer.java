package com.taotao.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/1/23 17:35
 * @Version 1.0
 */
@Service
public class RabbitMsgConsumer implements MessageListener {

    private final Logger LOG = LoggerFactory.getLogger(RabbitMsgProducer.class);
    @Override
    public void onMessage(Message message) {
//        LOG.info("consumer receive message------->:{}", message);
        System.out.println("consumer receive message------->:{}" + message);
    }
}
