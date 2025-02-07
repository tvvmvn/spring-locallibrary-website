package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
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
public class AuthorController {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;

  AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  // Get all authors
  @GetMapping("/authors")
  public String getAll(Model model) {
    List<Author> authors = authorRepository.findAll();

    model.addAttribute("authors", authors);

    return "author_list";
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

  // Save an author
  @GetMapping("/author/create")
  public String create(Model model) {

    Author author = new Author();

    model.addAttribute("author", author);

    return "author_form";
  }

  @PostMapping("/author/create")
  public String greetingSubmit(@Valid Author author, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "author_form";
    }

    authorRepository.save(author);

    return "redirect:/authors";
  }

  // Update an author
  @GetMapping("/author/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {

    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("author", author);

    return "author_form";
  }

  // Delete an author
  @GetMapping("/author/{id}/delete")
  public String deleteGet(@PathVariable("id") Long id, Model model) {

    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("author", author);

    int bookCount = bookRepository.countByAuthorId(id);

    model.addAttribute("bookCount", bookCount);

    return "author_delete";
  }

  @PostMapping("/author/{id}/delete")
  public String deletePost(@PathVariable("id") Long id) {

    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    authorRepository.deleteById(id);

    return "redirect:/authors";
  }
}
