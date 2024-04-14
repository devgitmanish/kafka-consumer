package com.kafka.consumer;

import com.kafka.dto.Customer;
import lombok.Data;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class KafkaEventConsumer {

    @KafkaListener(topics = "devin-topic-obj-cust", groupId = "devin-group-obj-cust")
    public void consumeMessage(Customer customer) {
        System.out.println("kafka consumer object messages...." + customer.toString());
    }

    @KafkaListener(topics = "devin-topic-obj-cust",
            groupId = "devin-group-obj-cust",
            topicPartitions = {@TopicPartition(topic = "devin-topic-obj-cust", partitions = {"2"})})
    public void consumeMessageReadFromParticularPartation(Customer customer) {
        System.out.println("kafka consumer object messages...." + customer.toString());
    }

    /*
        3000 = 3 seconds
        2nd call miltiply with 3*1.5 = 4.5 seconds call

        15000 = 15seconds

        what kind of exception we want to retry
     */
    @RetryableTopic(attempts = "4",
            backoff = @Backoff(delay = 3000, multiplier = 1.5, maxDelay = 15000),
    exclude = {NullPointerException.class, RuntimeException.class},
    include = {HttpServerErrorException.InternalServerError.class})
    @KafkaListener(topics = "devin-topic-obj-cust", groupId = "devin-group-obj-cust")
    public void consumeMessageRetry(Customer customer) {
        System.out.println("kafka consumer object messages...." + customer.toString());
    }

    @DltHandler
    public void listenDLT(){
        System.out.println("message locked to :- Kafka-error-handle-dlt");
    }
}
