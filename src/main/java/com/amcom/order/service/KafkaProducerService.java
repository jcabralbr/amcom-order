package com.amcom.order.service;

import com.amcom.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value(value = "${spring.kafka.dlq.topic:dlq_duplicate_orders}")
    private String dlqTopic;

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendToDlq(OrderDTO orderDTO) {
        kafkaTemplate.send(dlqTopic, orderDTO.orderId(), orderDTO);
    }
}
