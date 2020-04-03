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

    }

    @Override
    public void update(Book book, int id) throws SQLException {

    }

    @Override
    public Book get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        System.out.println(bookDaoSql.getBooksFromDb(dataSource).get(0).getAuthorId());
        return bookDaoSql.getBooksFromDb(dataSource);
    }

//    @Override
//    public void add(Author author) throws SQLException {
//        bookDaoSql.addAuthorToDb(dataSource, author);
//    }
//
//    @Override
//    public void update(Author author, int id) throws SQLException {
//        bookDaoSql.updateBookInDb(dataSource, id, book);
//    }
//
//    @Override
//    public Author get(int id) throws SQLException {
//        System.out.println(id);
//        return authorDaoSql.getAuthorFromDb(dataSource, id);
//    }
//
//    @Override
//    public List<Author> getAll() throws SQLException {
//        return authorDaoSql.getAuthorsFromDb(dataSource);
//    }
}
