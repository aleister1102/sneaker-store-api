package com.example.sneakerstore.controllers;

import com.example.sneakerstore.entities.Category;
import com.example.sneakerstore.models.CategoryRQ;
import com.example.sneakerstore.repositories.CategoryRepository;
import com.example.sneakerstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public @ResponseBody ResponseEntity<Category> createCategory(@RequestBody CategoryRQ categoryRQ) {
    Category createdCategory = categoryService.create(categoryRQ);
    return ResponseEntity.ok(createdCategory);
  }

  @GetMapping
  public @ResponseBody ResponseEntity<List<Category>> getCategories() {
    List<Category> categories = new ArrayList<>();
    categoryRepository.findAll().forEach(categories::add);
    return ResponseEntity.ok(categories);
  }

  @DeleteMapping
  public @ResponseStatus ResponseEntity<String> deleteCategories() {
    if (categoryRepository.count() > 0) {
      categoryRepository.deleteAll();
      return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories to be deleted");
    }
  }
}
