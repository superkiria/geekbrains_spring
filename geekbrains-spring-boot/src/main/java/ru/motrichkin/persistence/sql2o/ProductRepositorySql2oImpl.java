package ru.motrichkin.persistence.sql2o;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductSpecification;
import ru.motrichkin.persistence.interfaces.ProductRepository;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.min;

@Primary
@Component
public class ProductRepositorySql2oImpl implements ProductRepository {

    private final Sql2o sql2o;

    private static final String SELECT_PRODUCTS_QUERY = "select id, title, cost, from product";
    private static final String SELECT_ONE_PRODUCT_QUERY = "select id, title, cost, from product where id = :productId";
    private static final String SELECT_PRODUCTS_WITH_PAGE_QUERY =
            "select id, title, cost from product where ROWNUM between :offset and :end";
    private static final String SELECT_PRODUCTS_WITH_PRICE_QUERY =
            "select id, title, cost, from product where cost >= :minPrice and cost <= :maxPrice";

    public ProductRepositorySql2oImpl(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Product save(Product product) {
        try (Connection connection = sql2o.open()) {
            String query;
            if (product.getId() != null) {
                query = "update product set title = :title, cost = :cost where id = :id";
            } else {
                query = "insert into product (title, cost) values (:title, :cost)";
            }
            Long id = (Long) connection.createQuery(query, true)
                    .bind(product)
                    .executeUpdate().getKey();
            return connection.createQuery(SELECT_ONE_PRODUCT_QUERY, false)
                    .addParameter("productId", id)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .executeAndFetch(Product.class)
                    .stream().findAny().get();
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ONE_PRODUCT_QUERY, false)
                    .addParameter("productId", id)
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .executeAndFetch(Product.class)
                    .stream().findAny();
        }
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
        List<Product> products;
        long amountOfProducts;
        try (Connection connection = sql2o.open()) {
            products = connection.createQuery(SELECT_PRODUCTS_WITH_PAGE_QUERY, false)
                    .addParameter("offset", pageable.getOffset())
                    .addParameter("end", pageable.getOffset() + pageable.getPageSize())
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .executeAndFetch(Product.class);
            amountOfProducts = countProducts(connection);
        }
        return new PageImpl<>(
                products,
                pageable,
                amountOfProducts);
    }

    @Override
    public Page<Product> findAll(ProductSpecification productSpecification, Pageable pageable) {
        List<Product> products;
        long amountOfProducts;
        try (Connection connection = sql2o.open()) {
            products = connection.createQuery(SELECT_PRODUCTS_WITH_PRICE_QUERY, false)
                    .addParameter("minPrice", productSpecification.getMinPrice())
                    .addParameter("maxPrice", productSpecification.getMaxPrice())
                    .setColumnMappings(Product.COLUMN_MAPPINGS)
                    .executeAndFetch(Product.class);
            amountOfProducts = countProductsWithPrice(connection, productSpecification.getMinPrice(), productSpecification.getMaxPrice());
        }
        return new PageImpl<>(
                products.subList((int) pageable.getOffset(),
                                    min((int) pageable.getOffset() + pageable.getPageSize(), products.size())),
                pageable,
                amountOfProducts);
    }

    private Long countProducts(Connection connection) {
        return connection.createQuery("select count(id) from product")
                .executeAndFetch(Long.class).stream().findAny().get();
    }

    private Long countProductsWithPrice(Connection connection, int minPrice, int maxPrice) {
        return connection.createQuery("select count(id) from product where cost between :minPrice and :maxPrice")
                .addParameter("minPrice", minPrice)
                .addParameter("maxPrice", maxPrice)
                .executeAndFetch(Long.class).stream().findAny().get();
    }

}
