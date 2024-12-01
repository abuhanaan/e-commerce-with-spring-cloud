package com.abuhanaan.ecommerce.payment.service;

import com.abuhanaan.ecommerce.notification.NotificationProducer;
import com.abuhanaan.ecommerce.notification.request.PaymentNotificationRequest;
import com.abuhanaan.ecommerce.payment.model.entity.Payment;
import com.abuhanaan.ecommerce.payment.model.request.PaymentRequest;
import com.abuhanaan.ecommerce.payment.repository.PaymentRepository;
import com.abuhanaan.ecommerce.payment.util.PaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper mapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(@Valid PaymentRequest request) {
    Payment payment = paymentRepository.save(mapper.toPaymentRecord(request));
    notificationProducer.sendNotification(
        new PaymentNotificationRequest(request.orderReference(), request.amount(),
            request.paymentMethod(), request.customer().firstName(), request.customer().lastName(),
            request.customer().email())
    );
    return payment.getId();
  }
}
