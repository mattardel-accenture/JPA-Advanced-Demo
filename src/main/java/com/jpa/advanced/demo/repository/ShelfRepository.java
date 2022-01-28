package com.jpa.advanced.demo.repository;

import com.jpa.advanced.demo.entity.Shelf;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    @EntityGraph("shelf-book-graph")
    Shelf findByRoom(String room);
}
