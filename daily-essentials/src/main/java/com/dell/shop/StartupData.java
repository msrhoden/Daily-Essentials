package com.dell.shop;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dell.shop.product.Product;
import com.dell.shop.product.ProductService;
import com.dell.shop.user.User;
import com.dell.shop.user.UserService;

@Component
public class StartupData implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public StartupData(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        adminAccount();
        userAccount();
        exampleProducts();
    }

    private void userAccount(){
        User user = new User();

        user.setUsername("user");
        user.setPassword("user");
        user.setFirstName("Mauricio");
        user.setLastName("Rhoden");
        user.setCity("canoas");
        user.setPasswordConfirm("user");
        user.setGender("Male");
        user.setEmail("mauricio_rhoden@dell.com");
        user.setAge(36);

        userService.save(user);
    }

    private void adminAccount(){
        User admin = new User();
        
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setCity("Eldorado do Sul");
        admin.setPasswordConfirm("admin");
        admin.setGender("Male");
        admin.setEmail("admin@dell.com");

        userService.save(admin);
    }

    private void exampleProducts(){
        final String NAME = "Gremio Shirt";
        final String IMAGE_URL = "https://static.netshoes.com.br/produtos/camisa-gremio-i-2122-sn-torcedor-umbro-masculina/58/2IA-0598-058/2IA-0598-058_zoom1.jpg?ts=1619168939&";
        final String DESCRIPTION = "Example Description";
        final BigDecimal PRICE = BigDecimal.valueOf(52);
        final BigDecimal PRICE2 = BigDecimal.valueOf(35);
        final BigDecimal PRICE3 = BigDecimal.valueOf(12);
        final BigDecimal PRICE4 = BigDecimal.valueOf(44);
        
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();

        product1.setName("Gremio Shirt");
        product1.setImageUrl("https://static.netshoes.com.br/produtos/camisa-gremio-i-2122-sn-torcedor-umbro-masculina/58/2IA-0598-058/2IA-0598-058_zoom1.jpg?ts=1619168939&");
        product1.setDescription(DESCRIPTION);
        product1.setPrice(PRICE);

        product2.setName("Gremio Pants");
        product2.setImageUrl("https://static.netshoes.com.br/produtos/calca-moletom-gremio-masculina/06/NOP-0079-006/NOP-0079-006_zoom1.jpg?ts=1619460610&");
        product2.setDescription(DESCRIPTION);
        product2.setPrice(PRICE2);

        product3.setName("Gremio Hat");
        product3.setImageUrl("https://static.netshoes.com.br/produtos/bone-new-era-9twenty-gremio/06/IJX-4295-006/IJX-4295-006_zoom1.jpg?ts=1616068018&");
        product3.setDescription(DESCRIPTION);
        product3.setPrice(PRICE3);

        product4.setName("Gremio Shoes");
        product4.setImageUrl("https://static.netshoes.com.br/produtos/tenis-umbro-gremio-neptune-br-masculino/18/2IA-1053-118/2IA-1053-118_zoom1.jpg?ts=1622566802&");
        product4.setDescription(DESCRIPTION);
        product4.setPrice(PRICE4);
        
        product5.setName("Gremio Umbrela");
        product5.setImageUrl("https://http2.mlstatic.com/D_NQ_NP_961121-MLB20722793587_052016-O.webp");
        product5.setDescription(DESCRIPTION);
        product5.setPrice(PRICE4);
        
        

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);
    }
}
