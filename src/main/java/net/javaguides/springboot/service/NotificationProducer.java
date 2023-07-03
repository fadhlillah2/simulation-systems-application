package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private static final String TOPIC = "notifications";

    @Autowired
    private KafkaTemplate<String, Notification> kafkaTemplate;

    public void sendNotification(Notification notification){
        this.kafkaTemplate.send(TOPIC, notification);
    }
}