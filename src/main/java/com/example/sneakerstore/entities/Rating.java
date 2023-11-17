package com.example.sneakerstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rating")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer ratingId;
  private Integer starsCounter;
  private String comment;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonIgnore
  private Product product;
}
