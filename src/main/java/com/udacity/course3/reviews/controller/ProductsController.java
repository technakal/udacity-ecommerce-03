package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Product created successfully."),
        @ApiResponse(code = 400, message = "Bad request format. Please consult documentation for correct format."),
        @ApiResponse(code = 500, message = "The server is down. Please make sure that the Location microservice is running.")
})
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a product.
     */
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.of(productRepository.findById(id));

    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @GetMapping(value = "/")
    public List<Product> listProducts() {

        return (List<Product>) productRepository.findAll();

    }
}