package com.abuhanaan.ecommerce.payment.repository;

import com.abuhanaan.ecommerce.payment.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
