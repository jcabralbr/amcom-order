package com.amcom.order.service;

import com.amcom.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendToDlq(Order order) {
        kafkaTemplate.send("dlq_duplicate_orders", order);
    }
}
