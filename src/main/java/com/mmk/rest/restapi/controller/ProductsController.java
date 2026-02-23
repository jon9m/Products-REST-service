package com.mmk.rest.restapi.controller;

import com.mmk.rest.restapi.model.Product;
import com.mmk.rest.restapi.service.ProductsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productsService.getAllProducts();
    }

    @GetMapping("/paged")
    public Page<Product> getPages(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        return productsService.getPagedProducts(page, size);
    }

    @GetMapping("/search")
    public List<Product> getProductsByName(@RequestParam @Size(max = 10) String name) {
        return productsService.getProductsByName(name);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productsService.getProductById(id).orElse(Product.builder().name("NO_PRODUCT_EXIST").build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid Product product) {
        return productsService.addProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable String id, @RequestBody @Valid Product product) {
        return productsService.updateProduct(id, product);
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable String id){
        return productsService.deleteProduct(id);
    }
}
