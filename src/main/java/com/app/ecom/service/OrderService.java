package com.app.ecom.service;

import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.model.*;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    public Optional<OrderResponse> createOrder(String userId) {
        // validate cart items
        List<CartItem> cartItems = cartService.getCart(userId);
        if(cartItems.isEmpty()){
            return Optional.empty();
        }
        // validate user
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if(!userOpt.isPresent()){
            return Optional.empty();
        }
        User user = userOpt.get();
        // calculate total proice
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        // clear cart
        cartService.clearCart(userId);

        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponse mapToOrderResponse(Order savedOrder) {
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getOrderItems().stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                        )).toList(),
                savedOrder.getCreatedAt(),
                savedOrder.getUpdateAt()
        );

    }
}/*
 private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;;*/