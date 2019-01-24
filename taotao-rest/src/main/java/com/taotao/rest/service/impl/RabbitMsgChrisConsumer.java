package com.taotao.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/1/24 10:23
 * @Version 1.0
 */
public class RabbitMsgChrisConsumer implements MessageListener {

    private final Logger LOG = LoggerFactory.getLogger(RabbitMsgProducer.class);
    @Override
    public void onMessage(Message message) {
//        LOG.info("consumer receive message------->:{}", message);
        System.out.println("RabbitMsgChrisConsumer receive message------->:{}" + message);
    }
}
