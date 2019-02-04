package com.oreilly.sacon.library.controllers;

import com.oreilly.sacon.library.availability.Availability;
import com.oreilly.sacon.library.catalog.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogController {

    @Autowired
    private Availability availability;

    @Autowired
    private Catalog catalog;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        model.addAttribute("books", catalog.getAllBooks());
        return "catalog";
    }

    @PostMapping(value = "/catalog/borrow")
    public String borrowBook(@Param("bookId") Long bookId) {
        availability.borrow(bookId);
        return "redirect:/catalog";
    }

}
