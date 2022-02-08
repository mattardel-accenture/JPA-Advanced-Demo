package com.jpa.advanced.demo.repositoryTests;

import com.jpa.advanced.demo.entity.Book;
import com.jpa.advanced.demo.entity.Shelf;
import com.jpa.advanced.demo.repository.ShelfRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ShelfRepositoryTest {

    @Autowired
    private ShelfRepository shelfRepo;

    @Test
    public void listShelfTest() throws Exception {
        Book a = new Book("title", "author", 12);
        Book b = new Book("title2", "author2", 122);
        Book c = new Book("title3", "author3", 123);

        List<Book> books = new ArrayList<>();
        books.add(a);
        books.add(b);
        books.add(c);

        Shelf shelf = new Shelf("Library", "First floor", books);
        shelfRepo.save(shelf);

        //List<Shelf> test = shelfRepo.findByRoom("Library");
//        assertEquals(shelf2.getId(), 1L);








    }



}
