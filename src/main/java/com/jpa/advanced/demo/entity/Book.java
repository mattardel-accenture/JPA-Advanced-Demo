package com.jpa.advanced.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
//@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private static Log log = LogFactory.getLog(Book.class);

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private double price;
    private Boolean isDeleted = false;
    @Version
    private Long version;

    @ManyToOne
    @JsonBackReference
    private Shelf shelf;

    @ManyToMany(mappedBy = "books", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Genre> genres = new ArrayList<>();

    public Book(String title, String author, double price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @PrePersist
    public void logNewBookPrePersist() {
        log.info("Attempting to add new book entitled: " + title);
    }

//    @PostPersist
//    public void logNewUserAdded() {
//        log.info("Added user '" + userName + "' with ID: " + id);
//    }
//
//    @PreRemove
//    public void logUserRemovalAttempt() {
//        log.info("Attempting to delete user: " + userName);
//    }
//
//    @PostRemove
//    public void logUserRemoval() {
//        log.info("Deleted user: " + userName);
//    }
//
//    @PreUpdate
//    public void logUserUpdateAttempt() {
//        log.info("Attempting to update user: " + userName);
//    }
//
//    @PostUpdate
//    public void logUserUpdate() {
//        log.info("Updated user: " + userName);
//    }
//
//    @PostLoad
//    public void logUserLoad() {
//        fullName = firstName + " " + lastName;
//    }
}
