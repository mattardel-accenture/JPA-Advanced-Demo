package com.jpa.advanced.demo.repository;

import com.jpa.advanced.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("select book from Book book where book.isDeleted = false")
    @Override
    public List<Book> findAll();
    public List<Book> findByAuthor(String author);
    public Optional<Book> findByIsbn(String isbn);
}
