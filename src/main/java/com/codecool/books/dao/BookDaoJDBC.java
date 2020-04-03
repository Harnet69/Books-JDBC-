package com.codecool.books.dao;

import com.codecool.books.model.Book;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class BookDaoJDBC implements BookDao {
    private DataSource dataSource;
    private BookDaoSql bookDaoSql; // work with sql queries

    public BookDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
        this.bookDaoSql = new BookDaoSql();
    }

    @Override
    public void add(Book book) throws SQLException {
        bookDaoSql.addBookToDb(dataSource, book);
    }

    @Override
    public void update(Book book, int id) throws SQLException {
        bookDaoSql.updateBookInDb(dataSource, id, book);
    }

    @Override
    public Book get(int id) throws SQLException {
        return bookDaoSql.getBookFromDb(dataSource, id);
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return bookDaoSql.getBooksFromDb(dataSource);
    }
}
