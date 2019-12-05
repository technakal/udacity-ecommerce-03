package com.udacity.course3.reviews.mysql.repository;

import com.udacity.course3.reviews.mysql.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findByName(String productName);

    // todo: find by price range?
    // todo: find by rating?
    // todo: find by instock?
}
