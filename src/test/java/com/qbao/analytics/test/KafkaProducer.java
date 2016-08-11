package com.qbao.analytics.test;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer 
{
    private final Producer<String, String> producer;

    public KafkaProducer(){
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "slave1.qbao.com:9092,slave2.qbao.com:9092,slave4.qbao.com:9092");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks","-1");
        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    public void produce(String topic ,String key,String value) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.send(new KeyedMessage<String, String>(topic, key ,value));
    }

}