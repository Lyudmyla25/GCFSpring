package org.example.gcfspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.gcfspring.dto.BookDTO;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

//    @OneToOne
//    @JoinColumn(name = "isbn_id")
//    private ISBN isbn;
//
//    @ManyToMany
//    @JoinTable(
//        name = "book_reader",
//        joinColumns = @JoinColumn(name = "book_id"),
//        inverseJoinColumns = @JoinColumn(name = "reader_id")
//    )
//    private Set<Reader> readers = new HashSet<>();

    public Book() {}

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public BookDTO toBookDTO() {
        return new BookDTO(title, author != null ? author.getName() : null);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

//    public ISBN getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(ISBN isbn) {
//        this.isbn = isbn;
//    }
//
//    public Set<Reader> getReaders() {
//        return readers;
//    }
//
//    public void setReaders(Set<Reader> readers) {
//        this.readers = readers;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
