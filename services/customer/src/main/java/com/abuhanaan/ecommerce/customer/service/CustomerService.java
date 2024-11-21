package com.abuhanaan.ecommerce.customer.service;

import com.abuhanaan.ecommerce.customer.exception.CustomerNotFoundException;
import com.abuhanaan.ecommerce.customer.model.entity.Customer;
import com.abuhanaan.ecommerce.customer.model.request.CustomerRequest;
import com.abuhanaan.ecommerce.customer.model.response.CustomerResponse;
import com.abuhanaan.ecommerce.customer.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public String createCustomer(CustomerRequest request) {
    Customer customer = customerRepository.save(customerMapper.toCustomer(request));
    return customer.getId();
  }

  public void updateCustomer(CustomerRequest request) {
    var customer = customerRepository.findById(request.id()).
        orElseThrow(() -> new CustomerNotFoundException(
            String.format("No Customer found with the provided id:: %s", request.id())
        ));
    mergeCustomer(customer, request);
    customerRepository.save(customer);
  }

  private void mergeCustomer(Customer customer, CustomerRequest request) {
    if(!StringUtils.isEmpty(request.firstName())) {
      customer.setFirstName(request.firstName());
    }
    if(!StringUtils.isEmpty(request.lastName())) {
      customer.setLastName(request.lastName());
    }
    if(!StringUtils.isEmpty(request.email())) {
      customer.setEmail(request.firstName());
    }
    if(request.address() != null) {
      customer.setFirstName(request.firstName());
    }
  }

  public List<CustomerResponse> findAllCustomers() {
    List<Customer> customers = customerRepository.findAll();
    List<CustomerResponse> customerResponses = customers.stream()
        .map(customerMapper::fromCustomer)
        .collect(Collectors.toList());
    return customerResponses;
  }

  public Boolean existsById(String customerId) {
//    return customerRepository.existsById(customerId);
    return customerRepository.findById(customerId).isPresent();
  }

  public CustomerResponse findById(String customerId) {
    return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(() -> new CustomerNotFoundException(String.format("No Customer found with the provided id:: %s", customerId)));
  }

  public void deleteCustomer(String customerId) {
    customerRepository.deleteById(customerId);
  }

}
