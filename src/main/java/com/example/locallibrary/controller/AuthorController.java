package com.example.locallibrary.controller;

import com.example.locallibrary.model.Author;
import com.example.locallibrary.model.Book;
import com.example.locallibrary.repository.AuthorRepository;
import com.example.locallibrary.repository.BookRepository;
import com.example.locallibrary.exception.ResourceNotFoundException;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.ui.Model;

@Controller
public class AuthorController {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;

  AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  // Get an author
  @GetMapping("/author/{id}")
  public String getOne(@PathVariable("id") Long id, Model model) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("author", author);

    List<Book> books = bookRepository.findByAuthorId(id);

    model.addAttribute("books", books);

    return "author_detail";
  }
}
