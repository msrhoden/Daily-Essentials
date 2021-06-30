package com.dell.shop.cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dell.shop.product.Product;

@Service
public interface ShoppingCartService {
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearProducts();
    Map<Product, Integer> productsInCart();
    BigDecimal totalPrice();
    void cartCheckout();
    List<Cart> listCarts(long userId);
}
