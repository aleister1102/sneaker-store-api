package com.example.sneakerstore.repositories;

import com.example.sneakerstore.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

}
