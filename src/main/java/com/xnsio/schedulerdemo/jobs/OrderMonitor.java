package com.xnsio.schedulerdemo.jobs;

import com.xnsio.schedulerdemo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
public class OrderMonitor {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 10000)
    public void runEveryOneMinute() {
        System.out.println("Executing process orders");
        processOrders();
    }

    public void processOrders() {
        var orders = orderRepository.findAll();
        for (var order : orders) {
            kafkaTemplate.send("process-order", order.getId().toString());
        }
    }
}
