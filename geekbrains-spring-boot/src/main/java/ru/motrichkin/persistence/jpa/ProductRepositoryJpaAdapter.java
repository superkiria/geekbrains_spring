package ru.motrichkin.persistence.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductSpecification;
import ru.motrichkin.persistence.interfaces.ProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryJpaAdapter implements ProductRepository {

    @Autowired
    private ProductRepositoryJpaImpl productRepositoryJpa;

    @Override
    public Product save(Product product) {
        return productRepositoryJpa.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepositoryJpa.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepositoryJpa.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepositoryJpa.findAll(pageable);
    }

    @Override
    public Page<Product> findAll(ProductSpecification productSpecification, Pageable pageable) {
        return productRepositoryJpa.findAll(productSpecification.getProductSpecification(), pageable);
    }
}
