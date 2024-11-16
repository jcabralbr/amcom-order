package com.amcom.order.service;

import com.amcom.order.domain.Order;
import com.amcom.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public void processOrder(Order order) {
        if (orderRepository.existsById(order.getId())) {
            kafkaProducerService.sendToDlq(order);
        } else {
            order.calculateTotalPrice();
            orderRepository.save(order);
        }
    }
}
