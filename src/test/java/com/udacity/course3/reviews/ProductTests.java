package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
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
public class ProductTests {

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

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void testFindById() {

        String productName = "Oxo Lint Roller";

        // create product
        Product testProduct = new Product();
        testProduct.setName(productName);
        testProduct.setDescription("A reusable lint roller, extra useful for removing animal hair.");
        System.err.println("technakal | " + testProduct);

        // persist product
        System.err.println("technakal | Persisting product...");
        entityManager.persist(testProduct);

        // check that product has an id
        int testId = testProduct.getId();
        System.err.println("technakal | Persisted product id: " + testId);
        assertThat(testId).isNotNull();

        // retrieve product by id
        System.err.println("technakal | Retrieving persisted product...");
        Optional<Product> actualProduct = productRepository.findById(testId);

        // test that retrieved product matches created product
        System.err.println("technakal | Testing persisted product exists.");
        assertThat(actualProduct).isNotNull();
        if (actualProduct.isPresent()) {
            System.err.println("technakal | " + actualProduct.get());
            System.err.println("technakal | Testing persisted product matches expected.");
            assertEquals(testProduct, actualProduct.get());
        }
    }

    @Test
    public void testFindByProductName() {

        String productName = "Bananner";

        // create product
        Product testProduct = new Product();
        testProduct.setName(productName);
        testProduct.setDescription("A good, ol' fashion bananner. Yellow and yum.");
        testProduct.setPrice(0.99d);
        testProduct.setQuantity(12);
        System.err.println("technakal | " + testProduct);

        // persist product
        System.err.println("technakal | Persisting product...");
        entityManager.persist(testProduct);

        // retrieve product by id
        System.err.println("technakal | Retrieving persisted product...");
        Optional<Product> actualProduct = productRepository.findByName(productName);

        // test that retrieved product matches created product
        System.err.println("technakal | Testing persisted product exists.");
        assertThat(actualProduct).isNotNull();
        if (actualProduct.isPresent()) {
            System.err.println("technakal | " + actualProduct.get());
            System.err.println("technakal | Testing persisted product matches expected.");
            assertEquals(testProduct, actualProduct.get());
        }
    }

}