package com.amcom.order.controller;

import com.amcom.order.domain.Order;
import com.amcom.order.dto.OrderViewDTO;
import com.amcom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewDTO> getOrderByOrderId(@PathVariable String orderId) {
        try {
            Order order = orderService.getOrderByOrderId(orderId);
            return ResponseEntity.ok(order.toOrderViewDTO());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
