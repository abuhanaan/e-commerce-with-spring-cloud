package com.abuhanaan.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
public record PurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @Positive(message = "Quantity is mandatory")
    double quantity
) {

}
