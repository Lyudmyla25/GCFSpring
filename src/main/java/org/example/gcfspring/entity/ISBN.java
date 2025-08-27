//package org.example.gcfspring.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//public class ISBN {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String code;
//
//    @OneToOne(mappedBy = "isbn")
//    private Book book;
//
//    // Getters and setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public String getCode() { return code; }
//    public void setCode(String code) { this.code = code; }
//    public Book getBook() { return book; }
//    public void setBook(Book book) { this.book = book; }
//}
