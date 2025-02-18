package com.abuhanaan.ecommerce.customer.service;

import com.abuhanaan.ecommerce.customer.model.entity.Customer;
import com.abuhanaan.ecommerce.customer.model.request.CustomerRequest;
import com.abuhanaan.ecommerce.customer.model.response.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest request) {
    if (request == null) {
      return null;
    }
    return Customer.builder()
        .id(request.id())
        .firstName(request.firstName())
        .lastName(request.lastName())
        .address(request.address())
        .email(request.email())
        .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    return new CustomerResponse(
        customer.getId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmail(),
        customer.getAddress()
    );
  }
}
