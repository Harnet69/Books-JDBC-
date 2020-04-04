package com.codecool.books.controller;

import com.codecool.books.dao.*;
import com.codecool.books.model.*;
import com.codecool.books.view.UserInterface;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserInterface ui = new UserInterface(System.in, System.out);
        new Main(ui).run();
    }

    UserInterface ui;
    Dao<Author> authorDao;
    Dao<Book> bookDao;

    Main(UserInterface ui) {
        this.ui = ui;
    }

    private void run() throws SQLException {
        setup();

        boolean running = true;

        while (running) {
            ui.printTitle("Main Menu");
            ui.printOption('a', "Authors");
            ui.printOption('b', "Books");
            ui.printOption('q', "Quit");
            switch (ui.choice("abq")) {
                case 'a':
                    new AuthorManager(ui, authorDao).run();
                    break;
                case 'b':
                    new BookManager(ui, bookDao).run();
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    private void setup() throws SQLException {
        ui.printOption('i', "In-memory database");
        ui.printOption('j', "JDBC database");
        switch (ui.choice("ij")) {
            case 'i':
                ui.println("Using in-memory database");
                authorDao = new AuthorDaoInMemory();
                bookDao = new BookDaoInMemory();
                createInitialData();
                break;
            case 'j':
                ui.println("Using JDBC");
                DataSource dataSource = connect();
                authorDao = new AuthorDaoJDBC(dataSource);
                bookDao = new BookDaoJDBC(dataSource);
                break;
        }
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName("books");
        dataSource.setUser("harnet");
        dataSource.setPassword("1234");

        ui.println("Trying to connect...");
        dataSource.getConnection().close();
        ui.println("Connection OK");

        return dataSource;
    }


    private void createInitialData() throws SQLException {
        ui.println("Creating initial data");

        Author author1 = new Author("J.R.R.", "Tolkien", Date.valueOf("1982-01-03"));
        Author author2 = new Author("Douglas", "Adams", Date.valueOf("1952-03-11"));
        Author author3 = new Author("George R. R.", "Martin", Date.valueOf("1948-09-20"));
        Author author4 = new Author("Frank", "Herbert", Date.valueOf("1920-10-08"));

        authorDao.add(author1);
        authorDao.add(author2);
        authorDao.add(author3);
        authorDao.add(author4);

        bookDao.add(new Book("Hobbit", 1));
        bookDao.add(new Book("Lord of the Rings",1));
        bookDao.add(new Book("Hitchhiker's Guide to the Galaxy", 2));
        bookDao.add(new Book("A Game of Thrones", 3));
        bookDao.add(new Book("Tuf Voyaging", 3));
        bookDao.add(new Book("Dune", 4));
    }

}
