package com.abuhanaan.ecommerce.order.utils;

import com.abuhanaan.ecommerce.order.model.constant.PaymentMethod;
import com.abuhanaan.ecommerce.order.model.entity.Order;
import com.abuhanaan.ecommerce.order.model.request.OrderRequest;
import com.abuhanaan.ecommerce.order.model.response.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


  public Order toOrder(OrderRequest request) {
    return Order.builder()
        .id(request.id())
        .reference(request.reference())
        .customerId(request.customerId())
        .paymentMethod(request.paymentMethod())
        .totalAmount(request.amount())
        .build();
  }

  public OrderResponse fromOrder(Order order) {
    return new OrderResponse(order.getId(), order.getReference(), order.getTotalAmount(),
        order.getPaymentMethod(), order.getCustomerId());
  }
}
