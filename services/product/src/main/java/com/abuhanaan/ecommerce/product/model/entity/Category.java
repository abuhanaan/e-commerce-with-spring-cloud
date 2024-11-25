package com.abuhanaan.ecommerce.product.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
  private List<Product> products;
}
