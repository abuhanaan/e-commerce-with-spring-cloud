package com.abuhanaan.ecommerce.payment.model.request;

import com.abuhanaan.ecommerce.payment.model.constant.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    Customer customer
) {

}
