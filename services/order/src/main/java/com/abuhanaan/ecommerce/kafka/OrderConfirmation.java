package com.abuhanaan.ecommerce.kafka;

import com.abuhanaan.ecommerce.customer.response.CustomerResponse;
import com.abuhanaan.ecommerce.order.model.constant.PaymentMethod;
import com.abuhanaan.ecommerce.product.response.PurchaseResponse;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
) {

}
