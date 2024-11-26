package com.abuhanaan.ecommerce.order.model.entity;

import com.abuhanaan.ecommerce.order.model.constant.PaymentMethod;
import com.abuhanaan.ecommerce.orderline.model.OrderLine;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false, name = "reference")
  private String reference;

  @Column(nullable = false, name = "total_amount")
  private BigDecimal totalAmount;

  @Column(nullable = false, name = "payment_method")
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Column(nullable = false, name = "customer_id")
  private String customerId;

  @OneToMany(mappedBy = "order")
  private List<OrderLine> orderLines;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;
}
