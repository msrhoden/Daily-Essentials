package com.dell.shop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findAllByOrderByIdAsc();
}
