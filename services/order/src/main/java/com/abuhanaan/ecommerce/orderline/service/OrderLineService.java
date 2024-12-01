package com.abuhanaan.ecommerce.orderline.service;

import com.abuhanaan.ecommerce.orderline.model.OrderLine;
import com.abuhanaan.ecommerce.orderline.repository.OrderLineRepository;
import com.abuhanaan.ecommerce.orderline.request.OrderLineRequest;
import com.abuhanaan.ecommerce.orderline.response.OrderLineResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

  private final OrderLineRepository orderLineRepository;
  private final OrderLineMapper mapper;


  public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
    OrderLine order = mapper.toOrderLine(orderLineRequest);
    return  orderLineRepository.save(order).getId();
  }

  public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
    return orderLineRepository.findAllByOrderId(orderId).stream()
        .map(mapper::toOrderLineResponse)
        .collect(Collectors.toList());
  }
}
