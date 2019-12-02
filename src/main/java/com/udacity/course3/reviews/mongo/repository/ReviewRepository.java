package com.udacity.course3.reviews.mongo.repository;

import com.udacity.course3.reviews.mysql.entity.Product;
import com.udacity.course3.reviews.mysql.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, Integer> {

    Optional<Review> findByUserName(String userName);

    List<Review> findAllByProduct(Product product);

}
