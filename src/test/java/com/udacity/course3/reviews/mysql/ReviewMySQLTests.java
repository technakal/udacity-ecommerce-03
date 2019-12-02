package com.udacity.course3.reviews.mysql;

import com.udacity.course3.reviews.mysql.entity.Product;
import com.udacity.course3.reviews.mysql.entity.Review;
import com.udacity.course3.reviews.mysql.repository.ProductRepository;
import com.udacity.course3.reviews.mysql.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewMySQLTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
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
        Review testReview = new Review();
        testReview.setUserName(username);
        testReview.setReviewText(reviewText);
        testReview.setRating(5);
        testReview.setProduct(testProduct);
        System.err.println("technakal | " + testReview);

        // persist review
        System.err.println("technakal | Persisting review...");
        entityManager.persist(testReview);

        // check that product has an id
        int testId = testReview.getId();
        System.err.println("technakal | Persisted review id: " + testId);
        assertThat(testId).isNotNull();

        // retrieve review by id
        System.err.println("technakal | Retrieving persisted review...");
        Optional<Review> actualReview = reviewRepository.findById(testId);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted review exists...");
        assertThat(actualReview).isNotNull();
        if (actualReview.isPresent()) {
            System.err.println("technakal | " + actualReview.get());
            System.err.println("technakal | Testing persisted review matches expected...");
            assertEquals(testReview, actualReview.get());
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
        Review testReview = new Review();
        testReview.setUserName(username);
        testReview.setReviewText(reviewText);
        testReview.setRating(5);
        testReview.setProduct(testProduct);
        System.err.println("technakal | " + testReview);

        // persist review
        System.err.println("technakal | Persisting review...");
        entityManager.persist(testReview);

        // retrieve review by username
        System.err.println("technakal | Retrieving persisted review...");
        Optional<Review> actualReview = reviewRepository.findByUserName(username);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted review exists...");
        assertThat(actualReview).isNotNull();
        if (actualReview.isPresent()) {
            System.err.println("technakal | " + actualReview.get());
            System.err.println("technakal | Testing persisted review matches expected...");
            assertEquals(testReview, actualReview.get());
        }
    }

}