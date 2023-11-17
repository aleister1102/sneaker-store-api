package com.example.sneakerstore.services;

import com.example.sneakerstore.entities.Product;
import com.example.sneakerstore.entities.Rating;
import com.example.sneakerstore.models.RatingRQ;
import com.example.sneakerstore.models.RatingRS;
import com.example.sneakerstore.repositories.ProductRepository;
import com.example.sneakerstore.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private RatingRepository ratingRepository;

  public Product findProduct(Integer productId) {
    return productRepository.findById(productId)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product not found"));
  }

  public Rating create(RatingRQ ratingRQ) {
    Product product = findProduct(ratingRQ.getProductId());

    Rating rating = Rating.builder()
            .starsCounter(ratingRQ.getStarsCounter())
            .comment(ratingRQ.getComment())
            .product(product)
            .build();

    return ratingRepository.save(rating);
  }

  public List<RatingRS> findAll() {
    List<RatingRS> ratingResponses = new ArrayList<>();
    ratingRepository.findAll().forEach((rating) -> {
      RatingRS ratingResponse = RatingRS.builder()
              .ratingId(rating.getRatingId())
              .starsCounter(rating.getStarsCounter())
              .comment(rating.getComment())
              .productId(rating.getProduct().getProductId())
              .build();
      ratingResponses.add(ratingResponse);
    });
    return ratingResponses;
  }

}
