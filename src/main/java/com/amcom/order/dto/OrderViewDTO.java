package com.amcom.order.dto;

import com.amcom.order.domain.OrderStatus;

import java.util.List;

public record OrderViewDTO(String orderId, List<OrderItemDTO> items, Double totalPrice, OrderStatus orderStatus) {
}
