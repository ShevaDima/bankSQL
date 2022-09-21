package com.solvd.bank.JacksonTask;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Book {
    @JsonProperty
    private String title;

    @JsonProperty
    private String author;

    @JsonProperty
    private String language;

    @JsonProperty
    private List<String> country;

    public Book() {
    }

    public Book(String title, String author, String language, List<String> country) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "com.solvd.bank.JacksonTask.Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", country=" + country +
                '}';
    }
}
