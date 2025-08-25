package org.example.gcfspring.service;

import org.example.gcfspring.entity.Book;
import org.example.gcfspring.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testSaveAndFindAll() {
        Book book1 = new Book(null, "A", "B");
        Book book2 = new Book(null, "C", "D");
        bookService.save(book1);
        bookService.save(book2);
        List<Book> books = bookService.findAll();
        assertEquals(2, books.size());
    }

    @Test
    void testFindById() {
        Book book = new Book(null, "A", "B");
        bookService.save(book);
        List<Book> books = bookService.findAll();
        Book saved = books.get(0);
        Optional<Book> found = bookService.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("A", found.get().getTitle());
    }

    @Test
    void testDeleteById() {
        Book book = new Book(null, "A", "B");
        bookService.save(book);
        List<Book> books = bookService.findAll();
        Book saved = books.get(0);
        bookService.deleteById(saved.getId());
        assertEquals(0, bookService.findAll().size());
    }
}
