package com.codecool.books.dao;

import com.codecool.books.model.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoSql {

    private List<Author> workOnDb(DataSource dataSource, String sql) throws SQLException {
        Statement stmt = dataSource.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Author> authorsFromDb = new ArrayList<>();

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String first = rs.getString("first_name");
            String last = rs.getString("last_name");
            Date birth = rs.getDate("birth_date");

            authorsFromDb.add(new Author(id, first, last, birth));
        }
        rs.close();

        return authorsFromDb;
    }

    // add author to a database
    public void addAuthorToDb(DataSource dataSource, Author author) throws SQLException {
        String sql = "INSERT INTO author (first_name, last_name, birth_date) VALUES (?,?,?)";
        PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
        statement.setString(1, author.getFirstName());
        statement.setString(2, author.getLastName());
        statement.setDate(3, author.getBirthDate());

        int update = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            long key = rs.getLong(1);
            System.out.println(key);
        }
        assert rs != null;
        rs.close();
    }


    //get all authors from database
    public List<Author> getAuthorsFromDb(DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM author ORDER BY id";
        return workOnDb(dataSource, sql);
    }

    //get an author from database
    public Author getAuthorFromDb(DataSource dataSource, int id) throws SQLException {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM author WHERE id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Author author = null;

        while(rs.next()){
            //Retrieve by column name
            int authorId  = rs.getInt("id");
            String first = rs.getString("first_name");
            String last = rs.getString("last_name");
            Date birth = rs.getDate("birth_date");

            author = new Author(authorId, first, last, birth);
        }
        rs.close();

        return author;
    }

    // update author in a database
    public void updateAuthorInDb(DataSource dataSource,int id, Author author) throws SQLException {
        String sql = "UPDATE author SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?";
//        String sql = "INSERT INTO author (first_name, last_name, birth_date) VALUES (?,?,?)";
        PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
        statement.setString(1, author.getFirstName());
        statement.setString(2, author.getLastName());
        statement.setDate(3, author.getBirthDate());
        statement.setInt(4, id);

        int update = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            long key = rs.getLong(1);
            System.out.println(key);
        }
        assert rs != null;
        rs.close();
    }
}
