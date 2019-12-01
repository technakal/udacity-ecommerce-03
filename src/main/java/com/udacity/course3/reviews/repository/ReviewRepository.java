package com.udacity.course3.reviews.repository;

// todo: configure as Spring repository

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    Optional<Review> findByUserName(String userName);

    List<Review> findAllByProduct(Product product);

}
