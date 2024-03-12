package edu.psu.sweng888.myapplication;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private String publication;
    private Long isbn;

    public Book() {

    }

    public Book(String title, String author, String publisher, String publication, Long isbn){
        this.title = title;
        this.author = author;
        this. publisher = publisher;
        this.publication = publication;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    private void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    private void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublication() {
        return publication;
    }
    private void setPublication(String publication) {
        this.publication = publication;
    }

    public Long getIsbn() {
        return isbn;
    }

    private void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
}
