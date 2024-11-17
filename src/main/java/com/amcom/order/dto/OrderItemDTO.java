package com.amcom.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

@Builder
@JsonSerialize
public record OrderItemDTO(String sku, Integer quantity, Double unitPrice) {
}
