package com.abuhanaan.ecommerce.orderline.service;

import com.abuhanaan.ecommerce.order.model.entity.Order;
import com.abuhanaan.ecommerce.orderline.model.OrderLine;
import com.abuhanaan.ecommerce.orderline.request.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

  public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
    return OrderLine.builder()
        .id(orderLineRequest.id())
        .order(Order.builder()
            .id(orderLineRequest.orderId()).build())
        .quantity(orderLineRequest.quantity())
        .productId(orderLineRequest.productId()).build();
  }
}
