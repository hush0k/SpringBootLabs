package kz.kbtu.lecture2.service;

import kz.kbtu.lecture2.dto.ProductAdd;
import kz.kbtu.lecture2.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopProductService {
    @Autowired
    ShopRepository shopRepository;

    public List<String> getProducts() {
        return shopRepository.getProducts();
    }

    public void addProduct(ProductAdd productAdd) {
        shopRepository.addProduct(productAdd.getProductName());
    }
}
