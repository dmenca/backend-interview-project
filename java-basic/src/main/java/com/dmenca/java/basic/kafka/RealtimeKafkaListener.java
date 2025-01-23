package com.dmenca.java.basic.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RealtimeKafkaListener {

    @KafkaListener(topics = "${kafka.realtime.topic}", containerFactory = "realtimeConsumer")
    public void listen(ConsumerRecord record){
        Object value = record.value();
        String str = value.toString();
        System.out.println("listen kafka:"+str);
    }
}
