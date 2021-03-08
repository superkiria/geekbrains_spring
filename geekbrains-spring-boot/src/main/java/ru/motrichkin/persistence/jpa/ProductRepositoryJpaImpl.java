package ru.motrichkin.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.interfaces.ProductRepository;

@Repository
public interface ProductRepositoryJpaImpl extends JpaRepository<Product, Long>, ProductRepository {

}
