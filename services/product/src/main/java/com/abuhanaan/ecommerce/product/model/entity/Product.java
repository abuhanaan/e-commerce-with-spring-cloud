package com.abuhanaan.ecommerce.product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  private BigDecimal price;
  private Double availableQuantity;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
