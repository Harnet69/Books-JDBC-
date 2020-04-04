package com.codecool.books.dao;

import com.codecool.books.model.Author;

import java.util.*;

public class AuthorDaoInMemory implements Dao<Author> {
    Map<Integer, Author> authors = new HashMap<>();
    int idCounter = 0;

    @Override
    public void add(Author author) {
        author.setId(idCounter);
        idCounter++;
        authors.put(author.getId(), author);
    }

    @Override
    public void update(Author author, int id) {
        authors.put(author.getId(), author);
    }

    @Override
    public Author get(int id) {
        return authors.get(id);
    }

    @Override
    public List<Author> getAll() {
        return new ArrayList<>(authors.values());
    }
}
