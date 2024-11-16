package com.amcom.order.dto;

import lombok.Builder;

@Builder
public record OrderItemDTO(String skuId, Integer quantity, Double unitPrice) {
}
