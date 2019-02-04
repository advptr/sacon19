package com.oreilly.sacon.library.controllers;

import com.oreilly.sacon.library.catalog.Catalog;
import com.oreilly.sacon.library.dao.Item;
import com.oreilly.sacon.library.repositories.BookRepository;
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

    @Autowired
    private Catalog catalog;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        model.addAttribute("books", catalog.getAllBooks());
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
