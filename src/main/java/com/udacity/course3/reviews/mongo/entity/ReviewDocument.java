package com.udacity.course3.reviews.mongo.entity;

import com.udacity.course3.reviews.mysql.entity.Comment;
import com.udacity.course3.reviews.mysql.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class ReviewDocument {

    @Id
    private ObjectId id;

    private String userName;

    private String reviewText;

    private int rating;

    private int productId;

    private List<Comment> comments = new ArrayList<>();

    public ReviewDocument() {
    }

    public ReviewDocument(String userName, String reviewText, int rating) {
        this.userName = userName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public ReviewDocument(Review review) {
        this.productId = review.getProduct().getId();
        this.userName = review.getUserName();
        this.reviewText = review.getReviewText();
        this.rating = review.getRating();
        this.comments = review.getComments();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // convenience method
    public void addComment(Comment comment) {
        if(this.comments == null) {
            this.comments = new ArrayList<>();
        }
        this.comments.add(comment);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", reviewText='" + reviewText + '\'' +
                ", rating=" + rating + '\'' +
                ", productId = " + productId + '\'' +
                ", comment count = " + comments.size() + '\'' +
                '}';
    }

}
