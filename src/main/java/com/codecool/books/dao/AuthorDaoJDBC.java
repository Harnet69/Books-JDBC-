package com.codecool.books.dao;

import com.codecool.books.model.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class AuthorDaoJDBC implements Dao<Author> {
    private DataSource dataSource;
    private AuthorDaoSql authorDaoSql; // work with sql queries

    public AuthorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
        this.authorDaoSql = new AuthorDaoSql();
    }

    @Override
    public void add(Author author) throws SQLException {
        authorDaoSql.addAuthorToDb(dataSource, author);
    }

    @Override
    public void update(Author author, int id) throws SQLException {
       authorDaoSql.updateAuthorInDb(dataSource, id, author);
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
