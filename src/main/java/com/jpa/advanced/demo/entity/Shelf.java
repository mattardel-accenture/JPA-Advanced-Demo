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
}
