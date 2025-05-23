package com.example.locallibrary.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  @NotEmpty(message = "Title is required")
  private String title;

  @NotEmpty(message = "Summary is required")
  @Column(length = 10000)
  private String summary;

  @Size(min = 1, message = "Genre is required")
  @ManyToMany
  private Set<Genre> genre;

  @NotNull(message = "Author is required")
  @ManyToOne
  private Author author;
  
  @NotEmpty(message = "ISBN is required")
  private String isbn;

  // Initializer
  public Book() {};

  public Book(String title, String summary, String isbn, Author author, Set<Genre> genre) {
    this.title = title;
    this.summary = summary;
    this.isbn = isbn;
    this.author = author;
    this.genre = genre;
  };

  // Getter and setter
  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSummary() {
    return this.summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Set<Genre> getGenre() {
    return this.genre;
  }

  public void setGenre(Set<Genre> genre) {
    this.genre = genre;
  }

  public Author getAuthor() {
    return this.author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getIsbn() {
    return this.isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  // e.g) /book/1
  public String getUrl() {
    return "/book/" + this.id;
  }
}