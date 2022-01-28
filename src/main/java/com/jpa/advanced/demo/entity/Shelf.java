package com.jpa.advanced.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "shelf-book-graph",
        attributeNodes = @NamedAttributeNode("books")
)
@Table(name = "shelf")
public class Shelf {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(
            mappedBy = "shelf",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();
    private String location;
    private String room;
    @Version
    private Long version;

    public Shelf() {}

    public Shelf (String location, String room, List<Book> books) {
        this.location = location;
        this.room = room;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
