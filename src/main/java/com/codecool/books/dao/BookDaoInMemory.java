package com.codecool.books.dao;

import com.codecool.books.model.Author;
import com.codecool.books.model.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoInMemory implements Dao<Book> {
    Map<Integer, Book> books = new HashMap<>();
    int idCounter = 0;

    @Override
    public void add(Book book) throws SQLException {
        book.setId(idCounter);
        idCounter++;
        books.put(book.getId(), book);
    }
    @Override
    public void update(Book item, int id) throws SQLException {

    }

    @Override
    public Book get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return new ArrayList<>(books.values());
    }
}
