package kz.kbtu.lecture2.controller;

import kz.kbtu.lecture2.service.ShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    ShopProductService shopProductService;

    @GetMapping
    public List<String> getShopOrders() {
        return shopProductService.getProducts();
    }


}
