package com.example.sneakerstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;

@SpringBootApplication
public class SneakerStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(SneakerStoreApplication.class, args);
  }

}
