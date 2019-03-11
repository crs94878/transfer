package com.sberbank.amount.transfer.configs;

import com.sberbank.amount.transfer.controllers.models.TransferResponseModel;
import com.sberbank.amount.transfer.services.validations.Validation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class AppConfig {

    @Value("${spring.datasource.url}")
    private String JDBC_URL;

    @Value("${spring.datasource.driver-class-name}")
    private String JDBC_DRIVER_CLASS;

    @Value("${spring.data.mongodb.username}")
    private String JDBC_USERNAME;

    @Value("${spring.datasource.password}")
    private String JDBC_PASSWORD;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER_CLASS);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);
        System.out.println(dataSource.getUrl());
        return dataSource;
    }

    @Bean
    public Validation validation(){
        return new Validation();
    }

    @Bean
    @Lazy
    public TransferResponseModel getResponseModel(){
        return new TransferResponseModel();
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource, @Autowired Environment env) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(hibernateProperties(env));
        builder.scanPackages("com.sberbank.amount.transfer.database.entities");
        return builder.buildSessionFactory();
    }

    private Properties hibernateProperties(Environment env) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.order_updates", env.getProperty("spring.jpa.hibernate.order.updates"));
        return properties;
    }
}
