package org.example.gcfspring.controller;

import org.example.gcfspring.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "Book 1", "Author 1"));
        books.add(new Book(2L, "Book 2", "Author 2"));
        books.add(new Book(3L, "Book 3", "Author 3"));
    }

    @GetMapping("")
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book(15L,"", ""));
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
        }
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        return "redirect:/books";
    }
}
