package com.example.locallibrary.controller;

import com.example.locallibrary.model.Genre;
import com.example.locallibrary.model.Book;
import com.example.locallibrary.repository.GenreRepository;
import com.example.locallibrary.repository.BookRepository;
import com.example.locallibrary.exception.ResourceNotFoundException;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.ui.Model;

@Controller
public class GenreController {

  private GenreRepository genreRepository;
  private BookRepository bookRepository;

  GenreController(GenreRepository genreRepository, BookRepository bookRepository) {
    this.genreRepository = genreRepository;
    this.bookRepository = bookRepository;
  }

  // Get an genre
  @GetMapping("/genre/{id}")
  public String getOne(@PathVariable("id") Long id, Model model) {
    Genre genre = genreRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("genre", genre);

    List<Book> books = bookRepository.findByGenreId(id);

    model.addAttribute("books", books);

    return "genre_detail";
  }
}
