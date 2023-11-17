package com.example.sneakerstore.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingRS {
  private Integer ratingId;
  private Integer starsCounter;
  private String comment;
  private Integer productId;
}
