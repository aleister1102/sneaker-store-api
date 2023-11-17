package com.example.sneakerstore.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoryRQ {
  @NotNull
  @Length(max = 100)
  private String name;
}
