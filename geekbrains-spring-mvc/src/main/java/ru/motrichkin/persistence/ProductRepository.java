package ru.motrichkin.persistence;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final HashMap<Long, Product> products = new HashMap<>();
    private AtomicLong id = new AtomicLong(0L);

    public void insert(Product product) {
        product.setId(id.getAndIncrement());
        products.put(product.getId(), product);
    }

    public Product getById(Long id) {
        return products.get(id);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

}
