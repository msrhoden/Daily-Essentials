package com.dell.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.dell.shop.product.Product;
import com.dell.shop.product.ProductService;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/","/index","/home"})
    public String home(@RequestParam(name = "search", required = false) String search, Model model){
    	if(StringUtils.isEmpty(search)) {
    		model.addAttribute("products", getAllProducts());
    	} else {
    		model.addAttribute("products", findByName(search));
    	}
        model.addAttribute("productsCount", productsCount());

        return "home";
    }
    

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    private List<Product> getAllProducts(){
        return productService.findAllByOrderByIdAsc();
    }

    private long productsCount(){
        return productService.count();
    }
    
    private List<Product> findByName(String name){
        return productService.findByName(name);
    }
}
