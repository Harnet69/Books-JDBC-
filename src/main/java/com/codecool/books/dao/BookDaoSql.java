package com.codecool.books.dao;

import com.codecool.books.model.Book;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoSql {

    private List<Book> workOnDb(DataSource dataSource, String sql) throws SQLException {
        Statement stmt = dataSource.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Book> booksFromDb = new ArrayList<>();

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String title = rs.getString("title");
            int authorId = rs.getInt("author_id");
            booksFromDb.add(new Book(id, title, authorId));
        }
        rs.close();

        return booksFromDb;
    }

    //get all authors from database
    public List<Book> getBooksFromDb(DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM book ORDER BY id";
        return workOnDb(dataSource, sql);
    }

    //get book from database
    public Book getBookFromDb(DataSource dataSource, int bookId) throws SQLException {
        String sql = "SELECT * FROM book WHERE id = ?";
        PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
        statement.setInt(1, bookId);
        ResultSet rs = statement.executeQuery();
        Book book = null;

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String title  = rs.getString("title");
            int authorId  = rs.getInt("author_id");

            book = new Book(id, title, authorId);
        }
        rs.close();

        return book;
    }

        // update book in a database
    public void updateBookInDb(DataSource dataSource,int id, Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author_id = ? WHERE id = ?";
        PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setInt(2, book.getAuthorId());
        statement.setInt(3, id);
        int update = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            long key = rs.getLong(3);
            System.out.println(key);
        }
        assert rs != null;
        rs.close();
    }

    // add book to a database
    public void addBookToDb(DataSource dataSource, Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author_id) VALUES (?,?)";
        PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setInt(2, book.getAuthorId());

        int update = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            long key = rs.getLong(2);
            System.out.println(key);
        }
        assert rs != null;
        rs.close();
    }
}
