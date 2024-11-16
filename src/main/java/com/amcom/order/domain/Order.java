package com.amcom.order.domain;

import com.amcom.order.dto.OrderDTO;
import com.amcom.order.dto.OrderItemDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private String id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    @Column(nullable = false)
    private Double totalPrice;

    public void calculateTotalPrice() {
        totalPrice = items.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }

    public OrderDTO toDTO() {
        var itemDTOs = this.items.stream()
                .map(item -> OrderItemDTO.builder()
                        .skuId(item.getSkuId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .collect(Collectors.toList());

        return new OrderDTO(this.id, this.totalPrice, itemDTOs);
    }
}

