package com.abuhanaan.ecommerce.order.repository;

import com.abuhanaan.ecommerce.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
