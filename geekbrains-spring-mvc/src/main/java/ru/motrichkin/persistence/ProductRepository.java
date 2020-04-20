package ru.motrichkin.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p where p.cost >= :minPrice")
    List<Product> filterByPrice(@Param("minPrice") Integer minPrice);

    @Query("from Product p where p.cost >= :minPrice and p.cost <= :maxPrice")
    List<Product> filterByPrice(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

}
