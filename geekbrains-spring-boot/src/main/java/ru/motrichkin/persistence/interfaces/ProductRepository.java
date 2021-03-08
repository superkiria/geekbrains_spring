package ru.motrichkin.persistence.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.motrichkin.persistence.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);
}
