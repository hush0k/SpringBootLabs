package kz.kbtu.lecture2.controller;

import kz.kbtu.lecture2.dto.ProductAdd;
import kz.kbtu.lecture2.repository.ShopRepository;
import kz.kbtu.lecture2.service.ShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {
    @Autowired
    private ShopProductService shopProductService;


    @GetMapping("/shop/products/service")
    public List<String> getProductsFromService() {
        return shopProductService.getProducts();
    }

    @PostMapping("/shop/add/product")
    public void addNewProduct(
            @RequestBody ProductAdd productAdd
    ) {
        shopProductService.addProduct(productAdd);
    }
}
