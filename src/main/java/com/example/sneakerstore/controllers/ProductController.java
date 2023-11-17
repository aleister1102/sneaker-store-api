package com.example.sneakerstore.controllers;

import com.example.sneakerstore.entities.Product;
import com.example.sneakerstore.entities.ProductDetail;
import com.example.sneakerstore.models.ProductRQ;
import com.example.sneakerstore.repositories.ProductRepository;
import com.example.sneakerstore.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @PostMapping
  @ResponseBody
  public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRQ productRQ) {
    Product createdProduct = productService.create(productRQ);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) Integer categoryId) {
    List<Product> products = categoryId == null
            ? productService.findAll()
            : productService.findByCategoryId(categoryId);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{productId}")
  @ResponseBody
  public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId) {
    Product product = productRepository
            .findById(productId)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product not found"));
    return ResponseEntity.ok(product);
  }

  @GetMapping("/{productId}/detail")
  @ResponseBody
  public ResponseEntity<ProductDetail> getProductDetailById(@PathVariable("productId") Integer productId) {
    ProductDetail productDetail = productService.findProductDetailById(productId);
    return ResponseEntity.ok(productDetail);
  }


  @PutMapping(path = "/{productId}")
  @ResponseBody
  public ResponseEntity<Product> updateProductById(@PathVariable Integer productId, @Valid @RequestBody ProductRQ productRQ) {
    Product updatedProduct = productService.update(productId, productRQ);
    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping
  @ResponseBody
  public ResponseEntity<String> deleteProducts() {
    if (productRepository.count() > 0) {
      productRepository.deleteAll();
      return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products to be deleted");
    }
  }

  @DeleteMapping(path = "/{productId}")
  @ResponseBody
  public ResponseEntity<String> deleteProductById(@PathVariable Integer productId) {
    Product product = productRepository.findById(productId).orElse(null);

    if (product != null) {
      productRepository.deleteById(productId);
      return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    } else {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product not found");
    }
  }
}
