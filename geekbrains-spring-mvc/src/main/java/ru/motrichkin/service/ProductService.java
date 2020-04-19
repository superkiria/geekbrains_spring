package ru.motrichkin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void insert(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(Integer minPrice, Integer maxPrice) {
        return productRepository.filterByPrice(minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(Integer minPrice) {
        return productRepository.filterByPrice(minPrice);
    }
}
