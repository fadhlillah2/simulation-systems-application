//package net.javaguides.springboot.web.controller;
//
//import net.javaguides.springboot.model.Notification;
//import net.javaguides.springboot.service.NotificationProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.concurrent.CopyOnWriteArrayList;
//
//@Controller
//@RequestMapping("/notifications")
//public class NotificationController {
//
//    private final SimpMessagingTemplate template;
//    private final NotificationProducer notificationProducer;
//    private final CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList<>();
//
//    @Autowired
//    public NotificationController(SimpMessagingTemplate template, NotificationProducer notificationProducer) {
//        this.template = template;
//        this.notificationProducer = notificationProducer;
//    }
//
//    @KafkaListener(topics = "notifications", groupId = "group_id")
//    public void consume(Notification notification) {
//        if (notification == null) {
//            System.err.println("Received null payload");
//            return;
//        }
//        notifications.add(notification);
//        this.template.convertAndSend("/topic/notification", notification);
//    }
//
//
//    @GetMapping
//    public String main(Model model) {
//        model.addAttribute("notifications", notifications);
//        return "notification_receiver";
//    }
//
//    @RequestMapping(value = "/send", method = {RequestMethod.GET, RequestMethod.POST})
//    public String sendNotification(Notification notification, Model model) {
//        model.addAttribute("notification", new Notification());
//
//        notificationProducer.sendNotification(notification);
//
//        this.template.convertAndSend("/topic/notification", notification);
//
//        return "redirect:/notification_sender";
//    }
//}
