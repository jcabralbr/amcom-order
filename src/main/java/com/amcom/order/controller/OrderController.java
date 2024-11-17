package com.amcom.order.controller;

import com.amcom.order.domain.Order;
import com.amcom.order.dto.ErrorResponse;
import com.amcom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable String orderId) {

        Optional<Order> optionalOrder = orderService.getOrderByOrderId(orderId);
        if (optionalOrder.isEmpty()) {
            String errorMessage = String.format("Pedido não encontrado para o orderId: %s", orderId);
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return ResponseEntity.status(404).body(errorResponse);
        }
        return ResponseEntity.ok(optionalOrder.get().toOrderViewDTO());
    }
}
