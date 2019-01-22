package com.taotao.rest.service.impl;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

/**
 * @Title 生产者监听-打印日志，在producer配置文件中开启
 * @Description: 生产者监听-打印日志，在producer配置文件中开启
 * @Author caoqiang
 * @Date 2019/1/22 9:40
 * @Version 1.0
 */
public class kafkaProducerListener implements ProducerListener {
    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerServer.class);

    /**
     * 发送消息成功后调用
     * @param topic
     * @param partition
     * @param key
     * @param value
     * @param recordMetadata
     */
    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        LOG.info("==========kafka发送数据成功（日志开始）==========");
        LOG.info("----------topic:"+topic);
        LOG.info("----------partition:"+partition);
        LOG.info("----------key:"+key);
        LOG.info("----------value:"+value);
        LOG.info("----------RecordMetadata:"+recordMetadata);
        LOG.info("~~~~~~~~~~kafka发送数据成功（日志结束）~~~~~~~~~~");
    }


    /**
     * 发送消息错误后调用
     * @param topic
     * @param partition
     * @param key
     * @param value
     * @param e
     */
    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception e) {
        LOG.info("==========kafka发送数据错误（日志开始）==========");
        LOG.info("----------topic:"+topic);
        LOG.info("----------partition:"+partition);
        LOG.info("----------key:"+key);
        LOG.info("----------value:"+value);
        LOG.info("----------Exception:"+e);
        LOG.info("~~~~~~~~~~kafka发送数据错误（日志结束）~~~~~~~~~~");
        e.printStackTrace();

    }

    /**
     * 方法返回值代表是否启动kafkaProducer监听器
     * @return
     */
    @Override
    public boolean isInterestedInSuccess() {
        LOG.info("///kafkaProducer监听器启动///");
        return true;
    }
}
