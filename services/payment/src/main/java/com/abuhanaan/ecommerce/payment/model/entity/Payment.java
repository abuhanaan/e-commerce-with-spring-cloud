package com.abuhanaan.ecommerce.payment.model.entity;

import com.abuhanaan.ecommerce.payment.model.constant.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(nullable = false)
  private Integer orderId;
  private BigDecimal amount;
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;
}
