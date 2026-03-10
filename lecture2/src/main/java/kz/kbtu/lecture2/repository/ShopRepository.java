package kz.kbtu.lecture2.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ShopRepository {
    List<String> products = new ArrayList<>(
            Arrays.asList("Milk", "Butter", "Chicken")
    );

    public List<String> getProducts() {
        return products;
    }

    public void addProduct(String product) {
        products.add(product);
    }


}
