package com.example.locallibrary.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.locallibrary.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class BookInstance {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  @NotNull(message = "Book is required")
  @ManyToOne
  private Book book;

  @Size(min = 2, message = "Imprint is required")
  private String imprint;

  @NotNull(message = "Status is required")
  private Status status;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueBack;

  // initializer
  public BookInstance() {};
  
  public BookInstance(Book book, String imprint, Status status, LocalDate dueBack) {
    this.book = book;
    this.imprint = imprint;
    this.status = status;
    this.dueBack = dueBack;
  };

  // getter and setter
  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Book getBook() {
    return this.book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public String getImprint() {
    return this.imprint;
  }

  public void setImprint(String imprint) {
    this.imprint = imprint;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDate getDueBack() {
    return this.dueBack;
  }

  public void setDueBack(LocalDate dueBack) {
    this.dueBack = dueBack;
  }

  public String getUrl() {
    return "/bookinstance/" + this.id;
  }
}