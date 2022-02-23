package com.jpa.advanced.demo.service;

import com.jpa.advanced.demo.entity.Book;
import com.jpa.advanced.demo.entity.Shelf;
import com.jpa.advanced.demo.repository.BookRepository;
import com.jpa.advanced.demo.entity.Book_;
import com.jpa.advanced.demo.entity.Shelf_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShelfService shelfService;

    public List<Book> getBooksByWeirdCriteria() {
        return bookRepository.findAll(new Specification<Book>() {

            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate q = cb.like(root.get(Book_.author), "H%");
                q = cb.and(q, cb.equal(root.join(Book_.shelf).get(Shelf_.room), "The Big Room"));
                return q;
            }
        });
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void saveBook(Book book) {
        if (book.getShelf() != null && book.getShelf().getId() != null) {
            addBookToShelf(book);
        }
        bookRepository.save(book);
    }

    public void addBookToShelf(Book book) {
        Long shelfId = book.getShelf().getId();
        if (shelfId != null) {
            Optional<Shelf> shelf = shelfService.getShelfById(shelfId);
            Shelf foundShelf = shelf.get();
            foundShelf.getBooks().add(book);
            book.setShelf(foundShelf);
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Boolean updateBook(Long id, Book book) {
        Optional<Book> foundBook = getBookById(id);
        //need to assert that foundBook isn't empty before we call get
        if (foundBook.isEmpty()){
            return Boolean.FALSE;
        }

        Book retrievedBook = foundBook.get();

        if (book.getShelf() != null) {
            retrievedBook.setShelf(book.getShelf());
        }
        if (book.getTitle() != null) {
            retrievedBook.setTitle(book.getTitle());
        }
//        if (book.getPrice() != null) {
//            retrievedBook.setPrice(book.getPrice());
//        }
        if (book.getAuthor() != null) {
            retrievedBook.setAuthor(book.getAuthor());
        }

        saveBook(retrievedBook);
        return Boolean.TRUE;
    }
}
