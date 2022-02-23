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
@Table(name = "shelf")
public class Shelf {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(
            mappedBy = "shelf",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();
    private String location;
    private String room;
    @Version
    private Long version;

    public Shelf(String location, String room, List<Book> books) {
        this.location = location;
        this.room = room;
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelf)) return false;

        Shelf other = (Shelf) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
