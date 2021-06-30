package com.dell.shop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.shop.user.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findById(long id);
    List<Cart> findByUser(User user);
    List<Cart> findAllByOrderByIdAsc();
}
