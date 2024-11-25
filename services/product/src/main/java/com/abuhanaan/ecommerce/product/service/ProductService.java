package com.abuhanaan.ecommerce.product.service;

import com.abuhanaan.ecommerce.product.exception.ProductPurchaseException;
import com.abuhanaan.ecommerce.product.model.entity.Product;
import com.abuhanaan.ecommerce.product.model.request.ProductPurchaseRequest;
import com.abuhanaan.ecommerce.product.model.request.ProductRequest;
import com.abuhanaan.ecommerce.product.model.response.ProductPurchaseResponse;
import com.abuhanaan.ecommerce.product.model.response.ProductResponse;
import com.abuhanaan.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  public Integer createProduct(ProductRequest request) {
    Product product = mapper.toProduct(request);
    return repository.save(product).getId();
  }

  public ProductResponse findById(Integer id) {
    return repository.findById(id)
        .map(mapper::toProductResponse)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
  }

  public List<ProductResponse> findAll() {
    return repository.findAll()
        .stream()
        .map(mapper::toProductResponse)
        .collect(Collectors.toList());
  }

//  @Transactional(rollbackFor = ProductPurchaseException.class)
  public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
    List<Integer> productIds = request
        .stream()
        .map(ProductPurchaseRequest::productId)
        .toList();
    List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);
    if (productIds.size() != storedProducts.size()) {
      throw new ProductPurchaseException("One or more products does not exist");
    }
    List<ProductPurchaseRequest> sortedRequest = request
        .stream()
        .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
        .toList();
    List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
    for (int i = 0; i < storedProducts.size(); i++) {
      Product product = storedProducts.get(i);
      ProductPurchaseRequest productRequest = sortedRequest.get(i);
      if (product.getAvailableQuantity() < productRequest.quantity()) {
        throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
      }
      Double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
      product.setAvailableQuantity(newAvailableQuantity);
      repository.save(product);
      purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
    }
    return purchasedProducts;
  }
}
