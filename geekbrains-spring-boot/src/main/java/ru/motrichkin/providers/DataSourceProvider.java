package ru.motrichkin.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
public class DataSourceProvider /*implements TransactionManagementConfigurer*/ {

    @Autowired
    private DataSource dataSource;

//    @Bean
//    @Override
//    @NonNull
//    public TransactionManager annotationDrivenTransactionManager() {
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean
    public Sql2o sql2o() {
        return new Sql2o(dataSource);
    }
}
