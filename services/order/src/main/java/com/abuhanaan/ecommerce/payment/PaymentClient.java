package com.abuhanaan.ecommerce.payment;

import com.abuhanaan.ecommerce.payment.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "product-service",
    url = "${spring.application.config.payment-url}"
)
public interface PaymentClient {

  @PostMapping
  public Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
