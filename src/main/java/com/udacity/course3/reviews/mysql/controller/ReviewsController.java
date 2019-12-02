package com.udacity.course3.reviews.mysql.controller;

import com.udacity.course3.reviews.mysql.entity.Product;
import com.udacity.course3.reviews.mysql.entity.Review;
import com.udacity.course3.reviews.mysql.repository.ProductRepository;
import com.udacity.course3.reviews.mysql.repository.ReviewRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Review created successfully."),
        @ApiResponse(code = 400, message = "Bad request format. Please consult documentation for correct format."),
        @ApiResponse(code = 500, message = "The server is down. Please make sure that the Location microservice is running.")
})
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a review for a product.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @PostMapping(value = "/products/{productId}")
    public ResponseEntity<Review> createReviewForProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody Review review
    ) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()) {
            review.setProduct(optionalProduct.get());
            return new ResponseEntity(reviewRepository.save(review), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()) {
            return ResponseEntity.ok(reviewRepository.findAllByProduct(optionalProduct.get()));
        }
        return ResponseEntity.notFound().build();

    }
}