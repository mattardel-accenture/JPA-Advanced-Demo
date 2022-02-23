package com.jpa.advanced.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private static Log log = LogFactory.getLog(Book.class);

    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    private String isbn;
    private String title;
    private String author;
    private double price;
    private Boolean isDeleted = false;
    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Shelf shelf;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @PrePersist
    public void logNewBookPrePersist() {
        log.info("Attempting to add new book entitled: " + title);
    }

    @PostPersist
    public void logNewBookAdded() {
        log.info("Added book '" + title + "' by author: " + author + " and ID: " + id);
    }

    @PreRemove
    public void logBookRemovalAttempt() {
        log.info("Attempting to delete book: " + title + " by author: " + author + " and ID: " + id);
    }

    @PostRemove
    public void logBookRemoval() {
        isDeleted = true;
        log.info("Deleted book: " + title + " by author: " + author + " and ID: " + id);
    }

    @PreUpdate
    public void logBookUpdateAttempt() {
        log.info("Attempting to update book: " + title + " by author: " + author + " and ID: " + id);
    }

    @PostUpdate
    public void logBookUpdate() {
        log.info("Updated book: " + title + " by author: " + author + " and ID: " + id);
    }

    @PostLoad
    public void logBookLoad() {
        log.info("Loaded book: " + title + " by author: " + author + " and ID: " + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book other = (Book) o;
        return Objects.equals(getIsbn(), other.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn());
    }
}
