package com.app.ecom.dto;

import com.app.ecom.model.OrderItem;
import com.app.ecom.model.OrderStatus;
import com.app.ecom.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private Enum<OrderStatus> status;
    private List<OrderItemDTO> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
