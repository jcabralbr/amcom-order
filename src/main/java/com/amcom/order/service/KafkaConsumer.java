package com.amcom.order.service;

import com.amcom.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "purchase_order", groupId = "order-group")
    public void consumeOrder(Order order) {
        orderService.processOrder(order);
    }
}

