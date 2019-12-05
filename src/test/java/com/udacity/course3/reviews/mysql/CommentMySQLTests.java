package com.udacity.course3.reviews.mysql;

import com.udacity.course3.reviews.mysql.entity.Comment;
import com.udacity.course3.reviews.mysql.entity.Product;
import com.udacity.course3.reviews.mysql.entity.Review;
import com.udacity.course3.reviews.mysql.repository.CommentRepository;
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
public class CommentMySQLTests {

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
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void testFindById() {

        String productName = "Super Soap";
        String reviewUser = "John Don Dalliman";
        String reviewText = "5/5 stars! I love this thing!";
        String commentUser = "Sandy Pintler";

        // create product
        Product testProduct = new Product();
        testProduct.setName(productName);

        // create review
        Review testReview = new Review();
        testReview.setUserName(reviewUser);
        testReview.setReviewText(reviewText);
        testReview.setProduct(testProduct);

        // create comment
        Comment testComment = new Comment();
        testComment.setUserName(commentUser);
        testComment.setCommentText("I don't think " + reviewUser + "'s review is very helpful.");
        testComment.setReview(testReview);

        // persist review
        System.err.println("technakal | Persisting comment...");
        entityManager.persist(testComment);

        // check that product has an id
        int testId = testComment.getId();
        System.err.println("technakal | Persisted comment id: " + testId);
        assertThat(testId).isNotNull();

        // retrieve review by id
        System.err.println("technakal | Retrieving persisted comment...");
        Optional<Comment> actualComment = commentRepository.findById(testId);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted comment exists...");
        assertThat(actualComment).isNotNull();
        if (actualComment.isPresent()) {
            System.err.println("technakal | " + actualComment.get());
            System.err.println("technakal | Testing persisted comment matches expected...");
            assertEquals(testComment, actualComment.get());
        }
    }

    @Test
    public void testFindByUserName() {

        String productName = "Bananner";
        String reviewUser = "John Don Dalliman";
        String reviewText = "That's one good " + productName + ".";
        String commentUser = "Sandy Pintler";

        // create product
        Product testProduct = new Product();
        testProduct.setName(productName);
        testProduct.setDescription("A good, ol' fashion bananner. Yellow and yum.");

        // create review
        Review testReview = new Review();
        testReview.setUserName(reviewUser);
        testReview.setReviewText(reviewText);
        testReview.setRating(5);
        testReview.setProduct(testProduct);
        System.err.println("technakal | " + testReview);

        // create comment
        Comment testComment = new Comment();
        testComment.setUserName(commentUser);
        testComment.setCommentText("I don't think " + reviewUser + "'s review is very helpful.");
        testComment.setReview(testReview);

        // persist review
        System.err.println("technakal | Persisting comment...");
        entityManager.persist(testComment);

        // retrieve review by username
        System.err.println("technakal | Retrieving persisted comment...");
        Optional<Comment> actualComment = commentRepository.findByUserName(commentUser);

        // test that retrieved review matches created review
        System.err.println("technakal | Testing persisted comment exists...");
        assertThat(actualComment).isNotNull();
        if (actualComment.isPresent()) {
            System.err.println("technakal | " + actualComment.get());
            System.err.println("technakal | Testing persisted comment matches expected...");
            assertEquals(testComment, actualComment.get());
        }
    }

}