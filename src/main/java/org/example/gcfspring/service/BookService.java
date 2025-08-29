package org.example.gcfspring.service;

import org.example.gcfspring.dto.BookDTO;
import org.example.gcfspring.entity.Book;
import org.example.gcfspring.exception.BookNotFoundException;
import org.example.gcfspring.storage.BookStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookStorage bookStorage;
    private final AuthorService authorService; // Assuming AuthorService is already defined

    public BookService(BookStorage bookStorage, AuthorService authorService) {
        this.bookStorage = bookStorage;
        this.authorService = authorService;
    }

    public List<Book> findAll() {
        // log getting all the books
        return bookStorage.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookStorage.findById(id);
    }

    public void save(Book book) {
        bookStorage.save(book);
    }

    public void deleteById(Long id) {
        bookStorage.deleteById(id);
    }

    public void deleteAll() {
        bookStorage.deleteAll();
    }

    public Optional<BookDTO> findBookDTOById(Long id) {
        return findById(id)
                .map(Book::toBookDTO);
    }

    public void updateBook(Long id, Book updatedBook) {
        if (findById(id).isEmpty()) {
            throw new BookNotFoundException();
        }
        updatedBook.setId(id);
        save(updatedBook);
    }

    public void saveFromDTO(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        // Try to find the author by name (or adapt to your needs)
        var authorOpt = authorService.findByName(dto.getAuthorName());
        if (authorOpt.isEmpty()) {
            throw new IllegalArgumentException("Author not found: " + dto.getAuthorName());
        }
        book.setAuthor(authorOpt.get());
        save(book);
    }
}
