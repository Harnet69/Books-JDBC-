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

    public static List<Author> getAuthorsFromDb(DataSource dataSource) throws SQLException {
        System.out.println(dataSource);
        Statement stmt = dataSource.getConnection().createStatement();
        String sql = "SELECT * FROM author";
        ResultSet rs = stmt.executeQuery(sql);
        List<Author> authorsFromDb = new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String first = rs.getString("first_name");
            String last = rs.getString("last_name");
            Date birth = rs.getDate("birth_date");
            authorsFromDb.add(new Author(id, first, last, birth));

            //Display values
//            System.out.print("ID: " + id);
//            System.out.print(", First: " + first);
//            System.out.println(", Last: " + last);
//            System.out.println(", Birth date: " + birth);
        }
        rs.close();
//        List<Author> authors = Arrays.asList(new Author("Adam", "Sder", new Date(1982)));
        return  authorsFromDb;
    }
}
