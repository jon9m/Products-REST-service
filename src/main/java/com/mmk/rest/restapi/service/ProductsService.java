package com.mmk.rest.restapi.service;

import com.mmk.rest.restapi.model.Product;
import com.mmk.rest.restapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    public boolean isInStock(String name, int quantity) {
        return productRepository.existsByNameAndQuantityIsGreaterThanEqual(name, quantity);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    public Product updateProduct(String id, Product product) {
        return productRepository.findById(Integer.valueOf(id)).map(existing
                -> {
            Product productToSave = Product.builder()
                    .id(Integer.parseInt(id))
                    .name(Objects.isNull(product.getName()) ? existing.getName() : product.getName())
                    .quantity(product.getQuantity() >= 0 ? product.getQuantity() : existing.getQuantity())
                    .build();

            return productRepository.save(productToSave);
        }).orElseThrow(() -> new RuntimeException("Product not found with id to update {}" + id));

    }

    public Product deleteProduct(String id) {
        return productRepository.findById(Integer.valueOf(id)).map(existing -> {
            productRepository.delete(existing);
            return existing;
        }).orElseThrow(() -> new RuntimeException("Product not found with id to delete {}" + id));
    }

    public Page<Product> getPagedProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
}
