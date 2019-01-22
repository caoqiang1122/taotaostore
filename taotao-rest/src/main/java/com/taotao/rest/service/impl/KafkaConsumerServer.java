package com.taotao.rest.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @Title kafka监听器启动
 * @Description: 自动监听有消息是否需要消费
 * @Author caoqiang
 * @Date 2019/1/22 9:16
 * @Version 1.0
 */
public class KafkaConsumerServer implements MessageListener<String,String> {
    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerServer.class);

    /**
     * 监听器自动执行方法
     * 消息消费
     * 自动提交offset
     * 执行业务代码
     * （high level api 不提供offset管理，不指定offset进行消费）
     * @param consumerRecord
     */
    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        LOG.info("================kafkaConsumer开始消费");

        String topic = consumerRecord.topic();
        String key = consumerRecord.key();
        String value = consumerRecord.value();
        long offset = consumerRecord.offset();
        int partition = consumerRecord.partition();

        LOG.info("-----------topic:" + topic);
        LOG.info("-----------key:" + key);
        LOG.info("-----------value:" + value);
        LOG.info("-----------offset:" + offset);
        LOG.info("-----------partition:" + partition);

        LOG.info("================kafkaConsumer消费结束");
    }
}
