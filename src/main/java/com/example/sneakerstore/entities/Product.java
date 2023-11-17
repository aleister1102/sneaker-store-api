package com.example.sneakerstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer productId;
  private String name;
  private String description;
  private Float price;
  private String image;
  private String color;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_detail_id")
  private ProductDetail productDetail;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<Rating> ratings;

  @ManyToMany
  @JoinTable(
          name = "product_category",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<Category> categories;
}
