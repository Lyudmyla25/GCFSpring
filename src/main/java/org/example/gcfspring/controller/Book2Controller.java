package org.example.gcfspring.controller;

import org.example.gcfspring.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class Book2Controller {
    private final List<Book> books = new ArrayList<>();

    public Book2Controller() {
        books.add(new Book(1L, "Book 1", "Author 1"));
        books.add(new Book(2L, "Book 2", "Author 2"));
        books.add(new Book(3L, "Book 3", "Author 3"));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> listBooks(@RequestParam("isSample") Boolean isSample) {
        if (isSample) {
            return ResponseEntity.ok().body(books.subList(0, 1));
        }
        return ResponseEntity.ok().body(books);
    }
}
