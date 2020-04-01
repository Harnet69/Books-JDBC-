package com.codecool.books.model;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
