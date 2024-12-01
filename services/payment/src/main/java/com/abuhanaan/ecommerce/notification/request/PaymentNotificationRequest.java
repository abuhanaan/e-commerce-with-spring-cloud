package com.abuhanaan.ecommerce.notification.request;

import com.abuhanaan.ecommerce.payment.model.constant.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {

}
