package com.abuhanaan.ecommerce.product.repository;

import com.abuhanaan.ecommerce.product.model.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findAllByIdInOrderById(List<Integer> ids);
}
