package com.oreilly.sacon.library.catalog;

import com.oreilly.sacon.library.availability.Availability;
import com.oreilly.sacon.library.models.Book;
import com.oreilly.sacon.library.repositories.BookRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Catalog {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Availability availability;

    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(item -> new Book(item.getId(),
                        item.getName(),
                        item.getAuthor(),
                        item.getDescription(),
                        item.getRating(),
                        item.getImagePath(),
                        availability.inStock(item.getId())))
                .sorted(Comparator.comparing(Book::getName))
                .collect(Collectors.toList());
    }
}
