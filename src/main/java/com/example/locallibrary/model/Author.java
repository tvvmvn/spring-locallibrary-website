package com.example.locallibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Author {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @NotEmpty(message = "First name is required")
  private String firstName;

  @NotEmpty(message = "Last name is required")
  private String familyName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate deathDate;

  // Initializer
  public Author() {};

  public Author(String firstName, String familyName, LocalDate birthDate, LocalDate deathDate) {
    this.firstName = firstName;
    this.familyName = familyName;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
  };

  // Getter and setter
  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFamilyName() {
    return this.familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }
  
  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getDeathDate() {
    return this.deathDate;
  }

  public void setDeathDate(LocalDate deathDate) {
    this.deathDate = deathDate;
  }

  public String getName() {
    return this.firstName + " " +  this.familyName;
  }

  public String getUrl() {
    return "/author/" + this.id;
  }
}