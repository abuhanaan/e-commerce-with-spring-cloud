package com.abuhanaan.ecommerce.orderline.repository;

import com.abuhanaan.ecommerce.orderline.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

}
