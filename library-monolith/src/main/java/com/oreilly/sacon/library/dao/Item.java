package com.oreilly.sacon.library.dao;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    @Lob
    private String description;
    private int rating;
    private String imagePath;

    protected Item() {
    }

    public Item(String name, String author, String description, int rating, String imagePath) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.imagePath = imagePath;
    }

    public Item(Long id, String name, String author, String description, int rating, String imagePath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.rating = rating;
        this.imagePath = imagePath;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Long getId() {
        return id;
    }

}
