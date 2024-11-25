package com.abuhanaan.ecommerce.product.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductPurchaseException extends RuntimeException {

  public ProductPurchaseException(String s) {super(s);}
}
