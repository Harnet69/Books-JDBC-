package com.codecool.books.model;

public class Book {
    // null means not saved
    private Integer id;
    private int authorId;

    private String title;


    public Book(String title, int authorId) {
        this.title = title;
        this.authorId = authorId;
    }

    public Book(int id, String title, int authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Book %d: %s, %s",
                id, title, authorId);
    }
}
