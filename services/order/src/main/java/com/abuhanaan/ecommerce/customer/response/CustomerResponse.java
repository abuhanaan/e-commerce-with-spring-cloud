package com.abuhanaan.ecommerce.customer.response;

public record CustomerResponse(
    String id,
    String firstName,
    String lastName,
    String email
) {
}
