package com.amcom.order.dto;

import com.amcom.order.domain.Order;
import com.amcom.order.domain.OrderItem;
import com.amcom.order.domain.OrderStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

import java.util.List;

@Builder
@JsonSerialize
public record OrderDTO(String orderId, List<OrderItemDTO> items) {

    public Order toOrder() {
        var itemsOrder = this.items.stream()
                .map(item -> OrderItem.builder()
                        .sku(item.sku())
                        .quantity(item.quantity())
                        .unitPrice(item.unitPrice())
                        .build())
                .toList();

        return Order.builder()
                .orderId(this.orderId)
                .orderStatus(OrderStatus.CREATED)
                .items(itemsOrder)
                .build();
    }
}
