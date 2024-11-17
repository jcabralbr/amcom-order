package com.amcom.order.domain;

import com.amcom.order.dto.OrderDTO;
import com.amcom.order.dto.OrderItemDTO;
import com.amcom.order.dto.OrderViewDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void calculateTotalPrice() {
        totalPrice = items.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }

    public OrderDTO toOrderDTO() {
        var itemDTOs = this.items.stream()
                .map(item -> OrderItemDTO.builder()
                        .sku(item.getSku())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .collect(Collectors.toList());

        return new OrderDTO(this.orderId, itemDTOs);
    }

    public OrderViewDTO toOrderViewDTO() {
        var itemDTOs = this.items.stream()
                .map(item -> OrderItemDTO.builder()
                        .sku(item.getSku())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .collect(Collectors.toList());

        return new OrderViewDTO(this.orderId, itemDTOs, this.totalPrice, this.orderStatus);
    }
}

