package com.example.sneakerstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Integer productDetailId;
  private Float weight;
  private Timestamp manufacturedDate;
  private Timestamp expiredDate;

  public boolean equals(ProductDetail productDetail) {
    return this.weight.equals(productDetail.getWeight()) &&
            this.manufacturedDate.equals(productDetail.getManufacturedDate()) &&
            this.expiredDate.equals(productDetail.getExpiredDate());
  }
}
