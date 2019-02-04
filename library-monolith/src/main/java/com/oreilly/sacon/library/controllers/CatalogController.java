package com.oreilly.sacon.library.controllers;

import com.oreilly.sacon.library.dao.Item;
import com.oreilly.sacon.library.models.Book;
import com.oreilly.sacon.library.repositories.BookRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        List<Book> books = bookRepository.findAll()
                .stream()
                .map(item -> new Book(item.getId(), item.getName(), item.getAuthor(), item.getDescription(), item.getRating(),
                        item.getImagePath(), item.isAvailable()))
                .sorted(Comparator.comparingLong(item -> item.getId()))
                .collect(Collectors.toList());
        model.addAttribute("books", books);
        return "catalog";
    }

    @PostMapping(value = "/catalog/borrow")
    public String borrow(@Param("bookId") Long bookId) {
        Item book = bookRepository.findOne(bookId);
        book.setAvailable(false);
        bookRepository.save(book);
        return "redirect:/catalog";
    }
}
