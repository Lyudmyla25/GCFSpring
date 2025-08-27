package org.example.gcfspring.service;

import org.example.gcfspring.entity.Author;
import org.example.gcfspring.storage.AuthorStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorStorage authorStorage;

    public AuthorService(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public Author save(Author author) {
        return authorStorage.save(author);
    }

    public List<Author> findAll() {
        return authorStorage.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorStorage.findById(id);
    }

    public void deleteById(Long id) {
        authorStorage.deleteById(id);
    }

    public Optional<Author> findByName(String name) {
        return authorStorage.findAll().stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findFirst();
    }
}
