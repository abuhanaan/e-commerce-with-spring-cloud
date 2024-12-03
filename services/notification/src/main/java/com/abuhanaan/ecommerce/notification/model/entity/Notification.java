package com.abuhanaan.ecommerce.notification.model.entity;

import com.abuhanaan.ecommerce.kafka.order.OrderConfirmation;
import com.abuhanaan.ecommerce.kafka.payment.PaymentConfirmation;
import com.abuhanaan.ecommerce.notification.utils.NotificationType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Notification {

  @Id
  private String id;
  private NotificationType notificationType;
  private LocalDateTime notificationDate;
  private OrderConfirmation orderConfirmation;
  private PaymentConfirmation paymentConfirmation;
  private String title;
}
