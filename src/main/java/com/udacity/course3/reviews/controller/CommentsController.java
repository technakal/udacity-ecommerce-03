package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Comment created successfully."),
        @ApiResponse(code = 400, message = "Bad request format. Please consult documentation for correct format."),
        @ApiResponse(code = 500, message = "The server is down. Please make sure that the Location microservice is running.")
})
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Creates a comment for a review.
     * @param reviewId The id of the review.
     */
    @PostMapping(value = "/reviews/{reviewId}")
    public ResponseEntity<Comment> createCommentForReview(
            @PathVariable("reviewId") Integer reviewId,
            @RequestBody Comment comment
    ) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(optionalReview.isPresent()) {
            comment.setReview(optionalReview.get());
            return new ResponseEntity(commentRepository.save(comment), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * List comments for a review.
     * @param reviewId The id of the review.
     */
    @GetMapping(value = "/reviews/{reviewId}")
    public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(optionalReview.isPresent()) {
            return ResponseEntity.ok(commentRepository.findAllByReview(optionalReview.get()));
        }
        return ResponseEntity.notFound().build();

    }
}