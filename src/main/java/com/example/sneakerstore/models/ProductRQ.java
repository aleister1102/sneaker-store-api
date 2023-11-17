package com.example.sneakerstore.models;

import com.example.sneakerstore.entities.ProductDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class ProductRQ {
  @NotNull
  @Length(max = 100)
  private String name;

  @NotNull
  @Length(max = 255)
  private String description;

  @NotNull
  private Float price;

  @NotNull
  private String image;

  @NotNull
  private String color;

  @NotNull
  private ProductDetail productDetail;

  private List<Integer> categoryIds;
}
