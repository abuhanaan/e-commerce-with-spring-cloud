package com.abuhanaan.ecommerce.order.service;

import com.abuhanaan.ecommerce.customer.CustomerClient;
import com.abuhanaan.ecommerce.order.exception.BusinessException;
import com.abuhanaan.ecommerce.order.model.entity.Order;
import com.abuhanaan.ecommerce.order.model.request.OrderRequest;
import com.abuhanaan.ecommerce.order.model.response.OrderResponse;
import com.abuhanaan.ecommerce.order.product.ProductClient;
import com.abuhanaan.ecommerce.order.product.request.PurchaseRequest;
import com.abuhanaan.ecommerce.order.repository.OrderRepository;
import com.abuhanaan.ecommerce.order.utils.OrderMapper;
import com.abuhanaan.ecommerce.orderline.request.OrderLineRequest;
import com.abuhanaan.ecommerce.orderline.service.OrderLineService;
import jakarta.validation.Valid;
import java.util.List;
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

  public Integer createOrder(@Valid OrderRequest request) {
    var customer = customerClient.findCustomerById(request.customerId())
        .orElseThrow(() -> new BusinessException(String.format(
            "Cannot create Order:: No customer exist with the provided Id %s", request.customerId()
        )));
    this.productClient.purchaseProducts(request.products());
    Order order = this.orderRepository.save(mapper.toOrder(request));
    for (PurchaseRequest purchaseRequest: request.products()) {
        orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(),
            purchaseRequest.productId(), purchaseRequest.quantity()));
    }

//    todo: start payment process

    return null;
  }

  public List<OrderResponse> findAllOrders() {
    return null;
  }

  public OrderResponse findById(Integer orderId) {
    return null;
  }
}
