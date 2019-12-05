package com.udacity.course3.reviews.mysql.repository;

import com.udacity.course3.reviews.mysql.entity.Comment;
import com.udacity.course3.reviews.mysql.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Optional<Comment> findByUserName(String userName);

    List<Comment> findAllByReview(Review review);

    // todo: find by rating?

}
