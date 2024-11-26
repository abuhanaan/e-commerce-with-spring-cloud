package com.abuhanaan.ecommerce.orderline.service;

import com.abuhanaan.ecommerce.orderline.model.OrderLine;
import com.abuhanaan.ecommerce.orderline.repository.OrderLineRepository;
import com.abuhanaan.ecommerce.orderline.request.OrderLineRequest;
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
}
