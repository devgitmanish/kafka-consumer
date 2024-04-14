package com.kafka.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListner {

    @KafkaListener(topics ="devin-topic-2", groupId = "devin-group")
    public void consumeMessage1(String messgae){
        System.out.println("kafka consumer1 messages...."+messgae);
    }

    @KafkaListener(topics ="devin-topic-2", groupId = "devin-group")
    public void consumeMessage2(String messgae){
        System.out.println("kafka consumer2 messages...."+messgae);
    }

    @KafkaListener(topics ="devin-topic-2", groupId = "devin-group")
    public void consumeMessage3(String messgae){
        System.out.println("kafka consumer3 messages...."+messgae);
    }

    @KafkaListener(topics ="devin-topic-2", groupId = "devin-group")
    public void consumeMessage4(String messgae){
        System.out.println("kafka consumer4 messages...."+messgae);
    }
}
