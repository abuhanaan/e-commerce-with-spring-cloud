package com.abuhanaan.ecommerce.payment.request;

import com.abuhanaan.ecommerce.customer.response.CustomerResponse;
import com.abuhanaan.ecommerce.order.model.constant.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {

}
