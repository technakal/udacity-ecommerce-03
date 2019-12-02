package com.udacity.course3.reviews.mongo.entity;

import com.udacity.course3.reviews.mysql.entity.Product;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document("reviews")
public class Review {

    @Id
    private int id;

    private String userName;

    private String reviewText;

    private int rating;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            },
            fetch = FetchType.LAZY
    )
    private Product product;

    public Review() {
    }

    public Review(String userName, String reviewText, int rating) {
        this.userName = userName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", reviewText='" + reviewText + '\'' +
                ", rating=" + rating +
                ", product=" + product +
                '}';
    }

}
