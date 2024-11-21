package com.abuhanaan.ecommerce.customer.model.response;

import com.abuhanaan.ecommerce.customer.model.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
    String id,
    String firstName,
    String lastName,
    String email,
    Address address) {
}
