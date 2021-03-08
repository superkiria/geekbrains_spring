package ru.motrichkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.interfaces.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
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
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Specification<Product> productSpecification, Pageable pageable) {
        return productRepository.findAll(productSpecification, pageable);
    }

}
