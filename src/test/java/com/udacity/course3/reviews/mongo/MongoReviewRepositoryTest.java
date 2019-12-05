package com.udacity.course3.reviews.mongo;

import com.udacity.course3.reviews.mongo.entity.ReviewDocument;
import com.udacity.course3.reviews.mongo.repository.MongoReviewRepository;
import com.udacity.course3.reviews.mysql.entity.Product;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoReviewRepositoryTest {

    @Autowired
    private MongoReviewRepository reviewRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void testFindById() {

        String productName = "Bananner";
        String username = "John Don Dalliman";
        String reviewText = "That's one good " + productName + ".";

        // create product
        Product testProduct = new Product();
        testProduct.setName(productName);

        // create review
        ReviewDocument testReview = new ReviewDocument();
        testReview.setUserName(username);
        testReview.setReviewText(reviewText);
        testReview.setRating(5);
        testReview.setProductId(testProduct.getId());
        System.err.println("technakal | " + testReview);

        // persist review
        System.err.println("technakal | Persisting review...");
        reviewRepository.save(testReview);

        // check that product has an id
        ObjectId testId = testReview.getId();
        System.err.println("technakal | Persisted review id: " + testId);
        assertThat(testId).isNotNull();

        // retrieve review by id
        System.err.println("technakal | Retrieving persisted review...");
        Optional<ReviewDocument> actualReview = reviewRepository.findById(testId);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted review exists...");
        assertThat(actualReview).isNotNull();
        if (actualReview.isPresent()) {
            System.err.println("technakal | " + actualReview.get());
            System.err.println("technakal | Testing persisted review matches expected...");
            assertEquals(testReview.getReviewText(), actualReview.get().getReviewText());
            assertEquals(testReview.getUserName(), actualReview.get().getUserName());
        }
    }

    @Test
    public void testFindByUserName() {

        String productName = "Bananner";
        String username = "John Don Dalliman";
        String reviewText = "That's one good " + productName + ".";

        // create product
        Product testProduct = new Product();
        testProduct.setName(productName);
        testProduct.setDescription("A good, ol' fashion bananner. Yellow and yum.");

        // create review
        ReviewDocument testReview = new ReviewDocument();
        testReview.setUserName(username);
        testReview.setReviewText(reviewText);
        testReview.setRating(5);
        testReview.setProductId(testProduct.getId());
        System.err.println("technakal | " + testReview);

        // persist review
        System.err.println("technakal | Persisting review...");
        reviewRepository.save(testReview);

        // retrieve review by username
        System.err.println("technakal | Retrieving persisted review...");
        List<ReviewDocument> reviews = reviewRepository.findByUserName(username);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted review exists...");
        assertThat(reviews).isNotNull();
        if (!reviews.isEmpty()) {
            for(ReviewDocument review : reviews) {
                System.err.println("technakal | " + review);
                System.err.println("technakal | Testing persisted review matches expected...");
                assertEquals(testReview.getReviewText(), review.getReviewText());
                assertEquals(testReview.getUserName(), review.getUserName());
            }
        }
    }

    @Test
    public void testFindByProductId() {

        int productId = 1;
        String productName = "Bananner";
        String username = "John Don Dalliman";
        String reviewText = "That's one good " +  productName + ".";

        // create review
        ReviewDocument testReview = new ReviewDocument();
        testReview.setUserName(username);
        testReview.setReviewText(reviewText);
        testReview.setRating(5);
        testReview.setProductId(productId);
        System.err.println("technakal | " + testReview);

        // persist review
        System.err.println("technakal | Persisting review...");
        reviewRepository.save(testReview);

        // retrieve review by username
        System.err.println("technakal | Retrieving persisted review...");
        List<ReviewDocument> reviews = reviewRepository.findAllByProductId(productId);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted review exists...");
        assertThat(reviews).isNotNull();
        if (!reviews.isEmpty()) {
            for( ReviewDocument review : reviews) {
                System.err.println("technakal | " + review);
                System.err.println("technakal | Testing persisted review matches expected...");
                assertEquals(testReview.getReviewText(), review.getReviewText());
                assertEquals(testReview.getUserName(), review.getUserName());
            }
        }
    }
}
