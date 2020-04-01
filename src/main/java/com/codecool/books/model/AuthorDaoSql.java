package com.codecool.books.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    //get all authors from database
    public List<Author> getAuthorsFromDb(DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM author";
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
}
