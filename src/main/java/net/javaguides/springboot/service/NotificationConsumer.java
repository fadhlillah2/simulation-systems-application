package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private KafkaTemplate<String, Notification> kafkaTemplate;

    @KafkaListener(topics = "notifications", groupId = "group_id")
    public void consume(Notification notification) {
        System.out.println("Consumed message: " + notification.getMessage());
        this.template.convertAndSend("/topic/notifications", notification.getMessage());
    }

//    public List<Notification> getAllNotifications(String topicName) {
//        Consumer<String, Notification> consumer = new KafkaConsumer<>(kafkaTemplate.getProducerFactory().getConfigurationProperties());
//
//        consumer.subscribe(Arrays.asList(topicName));
//        List<Notification> result = new ArrayList<>();
//
//        ConsumerRecords<String, Notification> records = consumer.poll(Duration.ofMillis(1000));
//
//        for (ConsumerRecord<String, Notification> record : records) {
//            result.add(record.value());
//        }
//
//        consumer.close();
//
//        return result;
//    }
}
