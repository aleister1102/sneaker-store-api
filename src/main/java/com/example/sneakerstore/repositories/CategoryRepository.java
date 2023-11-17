package com.example.sneakerstore.repositories;

import com.example.sneakerstore.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
