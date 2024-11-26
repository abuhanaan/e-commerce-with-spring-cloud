package com.abuhanaan.ecommerce.order.product.response;

import java.math.BigDecimal;

public record PurchaseResponse(
    Integer productId,
    String name,
    String description,
    BigDecimal price,
    double quantity
) {

}
