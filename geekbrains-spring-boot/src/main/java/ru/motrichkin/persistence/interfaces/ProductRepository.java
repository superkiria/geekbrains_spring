package ru.motrichkin.persistence.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductSpecification;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAll(ProductSpecification productSpecification, Pageable pageable);
}
