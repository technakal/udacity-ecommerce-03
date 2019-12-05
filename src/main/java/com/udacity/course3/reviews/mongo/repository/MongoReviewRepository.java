package com.udacity.course3.reviews.mongo.repository;

import com.udacity.course3.reviews.mongo.entity.ReviewDocument;
import com.udacity.course3.reviews.mysql.entity.Product;
import com.udacity.course3.reviews.mysql.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoReviewRepository extends MongoRepository<ReviewDocument, Integer> {

    Optional<ReviewDocument> findById(ObjectId id);

    Optional<ReviewDocument> findByReviewText(String reviewText);

    List<ReviewDocument> findByUserName(String userName);

    List<ReviewDocument> findAllByProductId(int productId);

}
