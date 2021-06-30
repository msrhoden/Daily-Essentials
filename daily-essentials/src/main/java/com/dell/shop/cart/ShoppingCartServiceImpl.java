package com.dell.shop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.dell.shop.product.Product;
import com.dell.shop.user.User;
import com.dell.shop.user.UserRepository;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private Map<Product, Integer> cart = new LinkedHashMap<>();
    
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public ShoppingCartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
    	this.cartRepository = cartRepository;
		this.userRepository = userRepository;
	}

    @Override
    public void addProduct(Product product) {
        if (cart.containsKey(product)){
            cart.replace(product, cart.get(product) + 1);
        }else{
            cart.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1)
                cart.replace(product, cart.get(product) - 1);
            else if (cart.get(product) == 1) {
                cart.remove(product);
            }
        }
    }

    @Override
    public void clearProducts() {
        cart.clear();
    }

    @Override
    public Map<Product, Integer> productsInCart() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public BigDecimal totalPrice() {
        return cart.entrySet().stream()
                .map(k -> k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void cartCheckout() {
    	
    	Cart c = new Cart();
        
    	List<Product> products = new ArrayList<Product>();
    	for(Product product: cart.keySet()) {
    		products.add(product);
	    }
    	c.setProducts(products);
    	
        c.setUser(userRepository.findAll().get(0));
        
        c.setTotal(totalPrice());
        
		cartRepository.save(c);
    	
        cart.clear();
    }

	@Override
	public List<Cart> listCarts(long userId) {
		User user = userRepository.findById(userId);
		return cartRepository.findByUser(user);
	}
}
