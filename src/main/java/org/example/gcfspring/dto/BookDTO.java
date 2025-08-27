package org.example.gcfspring.dto;

public class BookDTO {
    private String title;
    private String authorName;

    public BookDTO(String title, String authorName) {
        this.title = title;
        this.authorName = authorName;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
}
