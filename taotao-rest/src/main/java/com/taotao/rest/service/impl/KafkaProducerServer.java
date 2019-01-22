package com.taotao.rest.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.utils.JsonUtil;
import com.taotao.rest.pojo.KafkaMsgConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @Title kafkaProducer模板，使用此模板发送消息
 * @Description: kafkaProducer模板，使用此模板发送消息
 * @Author caoqiang
 * @Date 2019/1/22 9:48
 * @Version 1.0
 */
public class KafkaProducerServer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param value messageValue
     * @param ifPartition 是否使用分区 0是\1不是
     * @param partitionNum 分区数 如果是否使用分区为0,分区数必须大于0
     * @param role 角色:bbc app erp...
     * @return
     */
    public Map<String,Object> sndMesForTemplate(String topic, Object value, String ifPartition,
                                                Integer partitionNum, String role) {

        //用来进行单元测试
//        ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-service.xml");
//        KafkaTemplate<String,String> kafkaTemplate = (KafkaTemplate<String,String>)context.getBean("KafkaTemplate");

        String key = role + "-" + value.hashCode();
        String valueString = JSONUtils.toJSONString(value);

        if (ifPartition.equals("0")) {
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key, valueString);
            Map<String, Object> res = checkProRecord(result);
            return res;
        } else {
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            Map<String, Object> res = checkProRecord(result);
            return res;
        }
    }


        /**
         * 根据key值获取分区索引
         * @param key
         * @param partitonNum
         * @return
         */
    public int getPartitionIndex(String key,int partitonNum){
        if (key == null){
            Random random = new Random();
            return random.nextInt(partitonNum);
        }else {
            int result = Math.abs(key.hashCode())%partitonNum;
            return result;
        }
    }


    /**
     * 检查发送返回结果
     * @param res
     * @return
     */
    private Map<String,Object> checkProRecord(ListenableFuture<SendResult<String, String>> res){
        Map<String,Object> m = new HashMap<String,Object>();

        if (res != null){
            try {
                //检查result结果集
                SendResult result = res.get();

                //检查recordMetadata的offset数据，不检查producerRecord
                Long offsetIndex = result.getRecordMetadata().offset();
                if (offsetIndex != null && offsetIndex > 0){
                    m.put("code",KafkaMsgConstant.SUCCESS_CODE);
                    m.put("message",KafkaMsgConstant.SUCCESS_MES);
                    return m;
                }else {
                    m.put("code",KafkaMsgConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message",KafkaMsgConstant.KAFKA_NO_OFFSET_MES);
                    return m;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                m.put("code",KafkaMsgConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message",KafkaMsgConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }catch (ExecutionException e){
                e.printStackTrace();
                m.put("code",KafkaMsgConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message",KafkaMsgConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        }else {
            m.put("code",KafkaMsgConstant.KAFKA_NO_RESULT_CODE);
            m.put("message",KafkaMsgConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }
}
