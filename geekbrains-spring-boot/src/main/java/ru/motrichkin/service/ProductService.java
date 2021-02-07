package ru.motrichkin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductRepository;
import ru.motrichkin.exceptions.NotFoundException;

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

    @Transactional
    public void update(Product product) {
        if (getById(product.getId()).isPresent()) {
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("No product with such ID: " + product.getId());
        }
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
    public Page<Product> getAllProducts(Integer minPrice, Integer maxPrice, Pageable pageable) {
        return productRepository.findAllByCostBetween(minPrice, maxPrice, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Integer minPrice, Pageable pageable) {
        return productRepository.findAllByCostGreaterThanEqual(minPrice, pageable);
    }
}
