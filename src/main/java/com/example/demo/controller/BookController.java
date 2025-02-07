package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Author;
import com.example.demo.model.Genre;
import com.example.demo.model.BookInstance;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookInstanceRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class BookController {

  private BookRepository bookRepository;
  private AuthorRepository authorRepository;
  private GenreRepository genreRepository;
  private BookInstanceRepository bookInstanceRepository;

  BookController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, BookInstanceRepository bookInstanceRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.genreRepository = genreRepository;
    this.bookInstanceRepository = bookInstanceRepository;
  }

  // Get all books
  @GetMapping("/books")
  public String getAll(Model model) {
    List<Book> books = bookRepository.findAll();

    model.addAttribute("books", books);

    return "book_list";
  }

  // Get a book
  @GetMapping("/book/{id}")
  public String getOne(@PathVariable("id") Long id, Model model) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("book", book);

    List<BookInstance> bookInstances = bookInstanceRepository.findByBookId(id);

    model.addAttribute("bookInstances", bookInstances);

    return "book_detail";
  }

  // Save a book
  @GetMapping("/book/create")
  public String greetingForm(Model model) {

    Book book = new Book();
    
    model.addAttribute("book", book);
    
    List<Author> authors = authorRepository.findAll();

    model.addAttribute("authors", authors);

    List<Genre> genres = genreRepository.findAll();

    model.addAttribute("genres", genres);

    return "book_form";
  }

  @PostMapping("/book/create")
  public String greetingSubmit(@Valid Book book, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {

      List<Author> authors = authorRepository.findAll();

      model.addAttribute("authors", authors);

      List<Genre> genres = genreRepository.findAll();

      model.addAttribute("genres", genres);
      
      return "book_form";
    }

    bookRepository.save(book);

    return "redirect:/books";
  }

  // Update a book
  @GetMapping("/book/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {

    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));
        
    model.addAttribute("book", book);
    
    List<Author> authors = authorRepository.findAll();

    model.addAttribute("authors", authors);

    List<Genre> genres = genreRepository.findAll();

    model.addAttribute("genres", genres);

    return "book_form";
  }

  // Delete a book
  @GetMapping("/book/{id}/delete")
  public String deleteGet(@PathVariable("id") Long id, Model model) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("book", book);
    
    int bookInstanceCount = bookInstanceRepository.countByBookId(id);
    
    model.addAttribute("bookInstanceCount", bookInstanceCount);

    return "book_delete";
  }

  @PostMapping("/book/{id}/delete")
  public String deletePost(@PathVariable("id") Long id) {

    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    bookRepository.deleteById(id);

    return "redirect:/books";
  }
}
