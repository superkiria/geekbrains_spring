package ru.motrichkin.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.motrichkin.persistence.Product;

@Repository
public interface ProductRepositoryJpaImpl extends JpaRepository<Product, Long> {

    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);
}
