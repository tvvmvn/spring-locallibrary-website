package com.example.demo.controller;

import com.example.demo.model.Genre;
import com.example.demo.model.Book;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Controller
public class GenreController {

  private GenreRepository genreRepository;
  private BookRepository bookRepository;

  GenreController(GenreRepository genreRepository, BookRepository bookRepository) {
    this.genreRepository = genreRepository;
    this.bookRepository = bookRepository;
  }

  // Get all genres
  @GetMapping("/genres")
  public String getAll(Model model) {
    List<Genre> genres = genreRepository.findAll();

    model.addAttribute("genres", genres);

    return "genre_list";
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

  // Save an genre
  @GetMapping("/genre/create")
  public String create(Model model) {

    Genre genre = new Genre();

    model.addAttribute("genre", genre);

    return "genre_form";
  }

  @PostMapping("/genre/create")
  public String greetingSubmit(@Valid Genre genre, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "genre_form";
    }

    genreRepository.save(genre);

    return "redirect:/genres";
  }

  // Update an genre
  @GetMapping("/genre/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {

    Genre genre = genreRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("genre", genre);

    return "genre_form";
  }

  // Delete an genre
  @GetMapping("/genre/{id}/delete")
  public String deleteGet(@PathVariable("id") Long id, Model model) {

    Genre genre = genreRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("genre", genre);

    int bookCount = bookRepository.countByGenreId(id);

    model.addAttribute("bookCount", bookCount);

    return "genre_delete";
  }

  @PostMapping("/genre/{id}/delete")
  public String deletePost(@PathVariable("id") Long id) {

    Genre genre = genreRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    genreRepository.deleteById(id);

    return "redirect:/genres";
  }
}
