package com.abuhanaan.ecommerce.product.model.response;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {

}
