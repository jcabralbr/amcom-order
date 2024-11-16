package com.amcom.order.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderDTO(String id, Double totalPrice, List<OrderItemDTO> items) {
}
