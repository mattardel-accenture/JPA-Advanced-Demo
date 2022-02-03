package com.jpa.advanced.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NamedEntityGraph(name = "book-graph",
        attributeNodes = @NamedAttributeNode("shelf")
)
@Table(name = "book")
@AllArgsConstructor
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

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
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

    public Book() {}

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

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Boolean getDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        isDeleted = deleted;
//    }
//
//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }
//
//    public Shelf getShelf() {
//        return shelf;
//    }
//
//    public void setShelf(Shelf shelf) {
//        this.shelf = shelf;
//    }
//
//    public List<Genre> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Book)){
            return false;
        } else {
            Book that = (Book)obj;

            return Objects.equals(this.title, that.title)
                    && Objects.equals(this.author, that.author);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
