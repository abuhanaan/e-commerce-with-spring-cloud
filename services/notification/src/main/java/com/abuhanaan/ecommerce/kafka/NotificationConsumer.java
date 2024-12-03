package com.abuhanaan.ecommerce.kafka;

import com.abuhanaan.ecommerce.email.EmailService;
import com.abuhanaan.ecommerce.kafka.order.OrderConfirmation;
import com.abuhanaan.ecommerce.kafka.payment.PaymentConfirmation;
import com.abuhanaan.ecommerce.notification.model.entity.Notification;
import com.abuhanaan.ecommerce.notification.repository.NotificationRepository;
import com.abuhanaan.ecommerce.notification.utils.NotificationType;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

  private final NotificationRepository notificationRepository;
  private final EmailService emailService;

  @KafkaListener(topics = "payment-topic")
  public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation)
      throws MessagingException {
    log.info(String.format("Consuming The Message From payment-topic Topic:: %s", paymentConfirmation));
    notificationRepository.save(Notification.builder()
        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
        .notificationDate(LocalDateTime.now())
        .paymentConfirmation(paymentConfirmation).build());

    String customerName = String.format("%s %s", paymentConfirmation.customerFirstName(),
        paymentConfirmation.customerLastName());
    emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(), customerName ,
        paymentConfirmation.amount(), paymentConfirmation.orderReference());
  }

  @KafkaListener(topics = "order-topic")
  public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation)
      throws MessagingException {
    log.info(String.format("Consuming The Message From order-topic Topic:: %s", orderConfirmation));
    notificationRepository.save(Notification.builder()
        .notificationType(NotificationType.ORDER_CONFIRMATION)
        .notificationDate(LocalDateTime.now())
        .orderConfirmation(orderConfirmation).build());

    String customerName = String.format("%s %s", orderConfirmation.customer().firstName(),
        orderConfirmation.customer().lastName());
    emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(), customerName ,
        orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());
  }
}
