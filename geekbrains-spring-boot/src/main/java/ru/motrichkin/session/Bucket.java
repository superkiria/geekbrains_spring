package ru.motrichkin.session;

import org.springframework.data.domain.Page;
import ru.motrichkin.persistence.Product;

public interface Bucket {

    Page<Product> getProducts();

    void addProduct(Product product);
}
