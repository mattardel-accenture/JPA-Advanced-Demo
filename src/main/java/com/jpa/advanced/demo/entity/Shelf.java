package com.jpa.advanced.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();
    private String location;
    private String room;
    @Version
    private Long version;

    public Shelf (String location, String room, List<Book> books) {
        this.location = location;
        this.room = room;
        this.books = books;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getRoom() {
//        return room;
//    }
//
//    public void setRoom(String room) {
//        this.room = room;
//    }
//
//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }

    public void addBook(Book book){
        books.add(book);
        book.setShelf(this);
    }

    public void removeBook(Book book){
        books.remove(book);
        book.setShelf(null);
    }



    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Shelf)){
            return false;
        } else {
            Shelf that = (Shelf)obj;

            return Objects.equals(this.books, that.books) && Objects.equals(this.location, that.location);
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(books, location);
    }
}
