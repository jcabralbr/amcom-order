package com.amcom.order.service;

import com.amcom.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "purchase_order", groupId = "order-group")
    public void consumeOrder(OrderDTO orderDTO) {
        log.info("recebendo o pedido {}, da fila purchase_order, para processamento", orderDTO.orderId());
        orderService.processOrder(orderDTO);
    }
}

