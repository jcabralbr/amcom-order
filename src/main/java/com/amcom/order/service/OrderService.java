package com.amcom.order.service;

import com.amcom.order.domain.Order;
import com.amcom.order.dto.OrderDTO;
import com.amcom.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public void processOrder(OrderDTO orderDTO) {
        Optional<Order> orderOptional = orderRepository.findByOrderId(orderDTO.orderId());
        if (orderOptional.isEmpty()) {
            Order order = orderDTO.toOrder();
            order.calculateTotalPrice();
            orderRepository.save(order);
        } else {
            kafkaProducerService.sendToDlq(orderDTO);
        }
    }

    public Order getOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
