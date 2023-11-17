package com.example.sneakerstore.services;

import com.example.sneakerstore.entities.Category;
import com.example.sneakerstore.models.CategoryRQ;
import com.example.sneakerstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public Category create(CategoryRQ categoryRQ) {
    Category newCategory = Category.builder()
            .name(categoryRQ.getName())
            .build();

    return categoryRepository.save(newCategory);
  }
}
