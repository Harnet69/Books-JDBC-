package com.codecool.books.model;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class AuthorDaoJDBC implements AuthorDao {
    private DataSource dataSource;
    private AuthorDaoSql authorDaoSql; // work with sql queries

    public AuthorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
        this.authorDaoSql = new AuthorDaoSql();
    }

    @Override
    public void add(Author author) {
        // TODO
    }

    @Override
    public void update(Author author) {
        // TODO
    }

    @Override
    public Author get(int id) throws SQLException {
        System.out.println(id);
        return authorDaoSql.getAuthorFromDb(dataSource, id);
    }

    @Override
    public List<Author> getAll() throws SQLException {
        return authorDaoSql.getAuthorsFromDb(dataSource);
    }
}
