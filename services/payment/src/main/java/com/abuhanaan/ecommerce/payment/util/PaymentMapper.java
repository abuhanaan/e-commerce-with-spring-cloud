package com.abuhanaan.ecommerce.payment.util;

import com.abuhanaan.ecommerce.payment.model.entity.Payment;
import com.abuhanaan.ecommerce.payment.model.request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

  public Payment toPaymentRecord(PaymentRequest request) {
    return Payment.builder()
        .id(request.id())
        .amount(request.amount())
        .paymentMethod(request.paymentMethod())
        .orderId(request.orderId())
        .build();
  };
}
