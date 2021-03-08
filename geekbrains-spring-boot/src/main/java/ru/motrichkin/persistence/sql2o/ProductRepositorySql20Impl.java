package ru.motrichkin.persistence.sql2o;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.interfaces.ProductRepository;
import ru.motrichkin.service.ProductService;

import java.util.List;
import java.util.Optional;

@Primary
@Component
public class ProductRepositorySql20Impl implements ProductRepository {

    private final Sql2o sql2o;

    private static final String SELECT_PRODUCTS_QUERY = "select id, title, cost, from product";

    public ProductRepositorySql20Impl(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    public List<Product> findAll() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_PRODUCTS_QUERY, false)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .executeAndFetch(Product.class);
        }
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable) {
        return null;
    }

    ;
}
