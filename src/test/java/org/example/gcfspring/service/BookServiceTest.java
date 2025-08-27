package org.example.gcfspring.service;

import org.example.gcfspring.entity.Author;
import org.example.gcfspring.entity.Book;
import org.example.gcfspring.storage.BookStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookStorage bookStorage;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Test Author");
        Book book1 = new Book(1L, "A");
        book1.setAuthor(author);
        Book book2 = new Book(2L, "C");
        book2.setAuthor(author);
        when(bookStorage.findAll()).thenReturn(List.of(book1, book2));
        List<Book> books = bookService.findAll();
        assertEquals(2, books.size());
        assertEquals("A", books.get(0).getTitle());
        assertEquals("C", books.get(1).getTitle());
    }

    @Test
    void testFindById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Test Author");
        Book book = new Book(1L, "A");
        book.setAuthor(author);
        when(bookStorage.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> found = bookService.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("A", found.get().getTitle());
    }

    @Test
    void testDeleteById() {
        doNothing().when(bookStorage).deleteById(1L);
        bookService.deleteById(1L);
        verify(bookStorage).deleteById(1L);
    }
}
