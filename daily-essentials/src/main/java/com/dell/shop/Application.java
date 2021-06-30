package com.dell.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dell.shop.cart.CartRepository;
import com.dell.shop.product.ProductRepository;
import com.dell.shop.user.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, ProductRepository.class, CartRepository.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
