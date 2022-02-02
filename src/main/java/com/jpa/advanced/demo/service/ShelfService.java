package com.jpa.advanced.demo.service;

import com.jpa.advanced.demo.entity.Book;
import com.jpa.advanced.demo.entity.Shelf;
import com.jpa.advanced.demo.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    public List<Shelf> getShelves() {
        return shelfRepository.findAll();
    }
    public Optional<Shelf> getShelfById(Long id){
        return(shelfRepository.findById(id));
    }

    public void saveShelf(Shelf shelf) {
        if (!shelf.getBooks().isEmpty()) {
            for (Book book : shelf.getBooks()) {
                book.setShelf(shelf);
            }
        }
        shelfRepository.save(shelf);
    }

    public void deleteShelf(Long id){
        shelfRepository.deleteById(id);
    }

}
