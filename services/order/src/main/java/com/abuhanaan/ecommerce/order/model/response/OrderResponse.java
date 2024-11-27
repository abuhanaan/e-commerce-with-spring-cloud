package com.abuhanaan.ecommerce.order.model.response;

import com.abuhanaan.ecommerce.order.model.constant.PaymentMethod;
import java.math.BigDecimal;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
) {

}
