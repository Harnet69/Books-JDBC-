package com.codecool.books.controller;

import com.codecool.books.dao.Dao;
import com.codecool.books.model.Book;
import com.codecool.books.view.UserInterface;

import java.sql.SQLException;

public class BookManager extends Manager {
    Dao<Book> bookDao;

    public BookManager(UserInterface ui, Dao<Book> bookDao) {
        super(ui);
        this.bookDao = bookDao;
    }

    @Override
    protected void add() throws SQLException {
        String title = ui.readString("Title", "X");
        int authorId = ui.readInt("Author is", 0);
        bookDao.add(new Book(title, authorId));
    }

    @Override
    protected String getName() {
        return "Book Manager";
    }

    @Override
    protected void list() throws SQLException {
        for (Book book: bookDao.getAll()) {
            ui.println(book);
        }
    }

    @Override
    protected void edit() throws SQLException {
        int id = ui.readInt("Book ID", 0);
        Book book = bookDao.get(id);
        if (book == null) {
            ui.println("Book not found!");
            return;
        }
        ui.println(book);

        String title = ui.readString("Title", "X");
        int authorId = ui.readInt("Author is", 0);

        book.setTitle(title);
        book.setAuthorId(authorId);
        bookDao.update(book, id);
    }
}
