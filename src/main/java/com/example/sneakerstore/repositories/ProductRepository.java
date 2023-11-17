package com.example.sneakerstore.repositories;

import com.example.sneakerstore.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
