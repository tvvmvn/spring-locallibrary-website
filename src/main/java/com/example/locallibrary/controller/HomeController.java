package com.example.locallibrary.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.locallibrary.model.Author;
import com.example.locallibrary.model.Book;
import com.example.locallibrary.model.Genre;
import com.example.locallibrary.repository.AuthorRepository;
import com.example.locallibrary.repository.BookRepository;
import com.example.locallibrary.repository.GenreRepository;

import org.springframework.ui.Model;

@Controller
public class HomeController {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private GenreRepository genreRepository;

  HomeController(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.genreRepository = genreRepository;
  }

  @GetMapping("/")
  public String home(Model model) {

    List<Author> authors = authorRepository.findAll();
    List<Book> books = bookRepository.findAll();
    List<Genre> genres = genreRepository.findAll();

    model.addAttribute("authors", authors);
    model.addAttribute("books", books);
    model.addAttribute("genres", genres);

    return "home";
  }

  @GetMapping("/login")
  public String login() {

    return "login";
  }
}
