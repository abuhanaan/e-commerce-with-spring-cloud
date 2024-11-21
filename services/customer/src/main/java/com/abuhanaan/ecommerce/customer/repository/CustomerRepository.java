package com.abuhanaan.ecommerce.customer.repository;

import com.abuhanaan.ecommerce.customer.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
