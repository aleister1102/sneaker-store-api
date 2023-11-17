package com.example.sneakerstore.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RatingRQ {
  @NotNull
  @Max(5)
  private Integer starsCounter;

  @NotNull
  @Length(max = 255)
  private String comment;

  @NotNull
  private Integer productId;
}
