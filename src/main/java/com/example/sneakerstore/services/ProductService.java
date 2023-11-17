package com.example.sneakerstore.services;

import com.example.sneakerstore.entities.*;
import com.example.sneakerstore.models.ProductRQ;
import com.example.sneakerstore.repositories.CategoryRepository;
import com.example.sneakerstore.repositories.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public List<Category> findCategories(List<Integer> categoryIds) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    return queryFactory
            .selectFrom(QCategory.category)
            .where(QCategory.category.categoryId.in(categoryIds))
            .fetch();
  }

  public Category findCategory(Integer categoryId) {
    return categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category not found"));
  }

  public Product create(ProductRQ productRQ) {
    List<Category> categories = findCategories(productRQ.getCategoryIds());

    Product newProduct = Product.builder()
            .name(productRQ.getName())
            .description(productRQ.getDescription())
            .price(productRQ.getPrice())
            .image(productRQ.getImage())
            .color(productRQ.getColor())
            .productDetail(productRQ.getProductDetail())
            .categories(categories)
            .build();

    return productRepository.save(newProduct);
  }

  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  public List<Product> findByCategoryId(Integer categoryId) {
    Category category = findCategory(categoryId);

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    return queryFactory
            .selectFrom(QProduct.product)
            .where(QProduct.product.categories.contains(category))
            .fetch();
  }

  public ProductDetail findProductDetailById(Integer productId) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    Product product = queryFactory
            .selectFrom(QProduct.product)
            .where(QProduct.product.productId.eq(productId))
            .fetchOne();

    ProductDetail productDetail;
    if (product == null) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product not found");
    } else {
      productDetail = product.getProductDetail();
    }
    return productDetail;
  }

  public Product update(Integer productId, ProductRQ productRQ) {
    Product product = productRepository
            .findById(productId)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product not found"));

    product.setName(productRQ.getName());
    product.setDescription(productRQ.getDescription());
    product.setPrice(productRQ.getPrice());
    product.setImage(productRQ.getImage());
    product.setColor(productRQ.getColor());

    // Check whether the product detail is changed
    if (!product.getProductDetail().equals(productRQ.getProductDetail()))
      product.setProductDetail(productRQ.getProductDetail());

    // Check whether the categories are changed
    List<Integer> currentCategoryIds = new ArrayList<>();
    product.getCategories().forEach(category -> currentCategoryIds.add(category.getCategoryId()));
    List<Integer> newCategoryIds = productRQ.getCategoryIds();
    if (!currentCategoryIds.equals(newCategoryIds)) {
      List<Category> categories = findCategories(newCategoryIds);
      product.setCategories(categories);
    }

    return productRepository.save(product);
  }
}
