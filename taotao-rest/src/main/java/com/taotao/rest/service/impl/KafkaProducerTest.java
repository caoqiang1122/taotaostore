package com.taotao.rest.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/1/22 10:20
 * @Version 1.0
 */
public class KafkaProducerTest {
    public static void main (String[] args){

        ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");

        KafkaProducerServer kafkaProducer = new KafkaProducerServer();
        String topic = "orderTopic";
        String value = "test";
        String ifPartition = "0";
        Integer partitionNum = 3;
        String role = "test";//用来生成key

        Map<String,Object> res = kafkaProducer.sndMesForTemplate
                (topic, value, ifPartition, partitionNum, role);

        System.out.println("测试结果如下：===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");

        System.out.println("code:"+code);
        System.out.println("message:"+message);
    }
}
