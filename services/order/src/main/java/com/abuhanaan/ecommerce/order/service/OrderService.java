package com.abuhanaan.ecommerce.order.service;

import com.abuhanaan.ecommerce.customer.CustomerClient;
import com.abuhanaan.ecommerce.kafka.OrderConfirmation;
import com.abuhanaan.ecommerce.kafka.OrderProducer;
import com.abuhanaan.ecommerce.exception.BusinessException;
import com.abuhanaan.ecommerce.order.model.entity.Order;
import com.abuhanaan.ecommerce.order.model.request.OrderRequest;
import com.abuhanaan.ecommerce.order.model.response.OrderResponse;
import com.abuhanaan.ecommerce.product.ProductClient;
import com.abuhanaan.ecommerce.product.request.PurchaseRequest;
import com.abuhanaan.ecommerce.product.response.PurchaseResponse;
import com.abuhanaan.ecommerce.order.repository.OrderRepository;
import com.abuhanaan.ecommerce.order.utils.OrderMapper;
import com.abuhanaan.ecommerce.orderline.request.OrderLineRequest;
import com.abuhanaan.ecommerce.orderline.service.OrderLineService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final CustomerClient customerClient;
  private final ProductClient productClient;
  private final OrderMapper mapper;
  private final OrderLineService orderLineService;
  private final OrderProducer orderProducer;

  public Integer createOrder(@Valid OrderRequest request) {
    var customer = customerClient.findCustomerById(request.customerId())
        .orElseThrow(() -> new BusinessException(String.format(
            "Cannot create Order:: No customer exist with the provided Id %s", request.customerId()
        )));
    List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(request.products());
    Order order = this.orderRepository.save(mapper.toOrder(request));
    for (PurchaseRequest purchaseRequest: request.products()) {
        orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(),
            purchaseRequest.productId(), purchaseRequest.quantity()));
    }

//    todo: start payment process

    orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(), request.amount(),
        request.paymentMethod(), customer, purchasedProducts));
    return order.getId();
  }

  public List<OrderResponse> findAllOrders() {
    return orderRepository.findAll().stream()
        .map(mapper::fromOrder)
        .collect(Collectors.toList());
  }

  public OrderResponse findById(Integer orderId) {
    return mapper.fromOrder(orderRepository.findById(orderId).orElseThrow(
        () -> new BusinessException(String.format("No order found with the provided id: %s", orderId))));
  }
}
