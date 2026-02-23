package com.mmk.rest.restapi.repository;

import com.mmk.rest.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByNameAndQuantityIsGreaterThanEqual(String name, int quantity);

    List<Product> findAllByNameContaining(String name);

}
