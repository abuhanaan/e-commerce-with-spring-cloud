package com.abuhanaan.ecommerce.payment.controller;

import com.abuhanaan.ecommerce.payment.model.request.PaymentRequest;
import com.abuhanaan.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping
  public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest request){
    return ResponseEntity.ok(paymentService.createPayment(request));
  }
}
