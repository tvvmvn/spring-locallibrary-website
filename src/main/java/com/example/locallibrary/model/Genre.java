package com.example.locallibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Genre {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @Size(min = 2, max=30)
  private String name;

  // Initializer
  public Genre() {};

  public Genre(String name) {
    this.name = name;
  };

  // Getter and setter
  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return "/genre/" + this.id;
  }
}