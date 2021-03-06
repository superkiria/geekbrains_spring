package ru.motrichkin.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCostGreaterThanEqual(@Param("minPrice") Integer minPrice, Pageable pageable);

    Page<Product> findAllByCostBetween(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, Pageable pageable);

    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);

}
