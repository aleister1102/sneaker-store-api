package com.example.sneakerstore.controllers;

import com.example.sneakerstore.entities.Rating;
import com.example.sneakerstore.models.RatingRQ;
import com.example.sneakerstore.models.RatingRS;
import com.example.sneakerstore.repositories.RatingRepository;
import com.example.sneakerstore.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.sneakerstore.entities.Product;
import com.example.sneakerstore.repositories.ProductRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/ratings")
public class RatingController {

  @Autowired
  private RatingService ratingService;

  @PostMapping
  @ResponseBody
  public ResponseEntity<Rating> createRating(@RequestBody RatingRQ ratingRQ) {
    Rating createdRating = ratingService.create(ratingRQ);
    return ResponseEntity.ok(createdRating);
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<RatingRS>> getRatings() {
    List<RatingRS> ratings = ratingService.findAll();
    return ResponseEntity.ok(ratings);
  }
}
