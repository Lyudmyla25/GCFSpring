package org.example.gcfspring.controller;

import org.example.gcfspring.dto.BookDTO;
import org.example.gcfspring.entity.Book;
import org.example.gcfspring.exception.BookNotFoundException;
import org.example.gcfspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books2")
public class Book2Controller {
    private final BookService bookService;

    @Autowired
    public Book2Controller(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> listBooks() {
        List<BookDTO> dtos = bookService.findAll().stream()
            .map(Book::toBookDTO)
            .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.findBookDTOById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(BookNotFoundException::new);
    }

    @GetMapping("not-handled/{id}")
    public ResponseEntity<BookDTO> getBookByIdNotHandles(@PathVariable Long id) {
        return bookService.findById(id)
            .map(book -> ResponseEntity.ok(book.toBookDTO()))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> createBook(@RequestBody BookDTO dto) {
        bookService.saveFromDTO(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isEmpty()) {
            throw new BookNotFoundException();
        }
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllBooks() {
        bookService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
