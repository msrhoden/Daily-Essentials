package com.dell.shop.user;

import java.util.List;

public interface UserService {
    void save(User user);
    void login(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    List<User> findAll();
}
