package com.example.sneakerstore.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer categoryId;

  @Column(name = "name", unique = true)
  private String name;

  @ManyToMany(mappedBy = "categories")
  @JsonIgnore
  private List<Product> products;
}
