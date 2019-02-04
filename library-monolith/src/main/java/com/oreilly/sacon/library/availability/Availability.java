package com.oreilly.sacon.library.availability;

import com.oreilly.sacon.library.dao.Item;
import com.oreilly.sacon.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Availability {

    @Autowired
    private BookAvailabilityRepository bookAvailabilityRepository;

    public void borrow(Long bookId) {
        Book book = bookAvailabilityRepository.findOne(bookId);
        book.borrow();
        bookAvailabilityRepository.save(book);
    }

}