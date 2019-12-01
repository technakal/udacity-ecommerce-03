package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@JsonIgnoreProperties({ "review" })
public class Comment {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            },
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "review_id")
    private Review review;

    // constructors
    public Comment() {
    }

    public Comment(String userName, String commentText) {
        this.userName = userName;
        this.commentText = commentText;
    }

    public Comment(int id, String userName, String commentText) {
        this.id = id;
        this.userName = userName;
        this.commentText = commentText;
    }

    // getters and setters
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    // other methods
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", comment='" + commentText + '\'' +
                '}';
    }

}
