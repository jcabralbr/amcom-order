package com.amcom.order.service;

import com.amcom.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "purchase_order", groupId = "order-group")
    public void consumeOrder(OrderDTO orderDTO) {
        orderService.processOrder(orderDTO);
    }
}

