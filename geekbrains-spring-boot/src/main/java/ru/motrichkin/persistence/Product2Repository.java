package ru.motrichkin.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class Product2Repository {

    private final Sql2o sql2o;

    private static final String SELECT_PRODUCTS_QUERY = "select id, title, cost, from product";

    public Product2Repository(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<ProductDto> findAll() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_PRODUCTS_QUERY, false)
                    .executeAndFetch(ProductDto.class);
        }
    };
}
