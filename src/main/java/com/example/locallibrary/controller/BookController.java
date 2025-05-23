package com.example.locallibrary.controller;

import com.example.locallibrary.exception.ResourceNotFoundException;
import com.example.locallibrary.model.Book;
import com.example.locallibrary.model.BookInstance;
import com.example.locallibrary.repository.BookInstanceRepository;
import com.example.locallibrary.repository.BookRepository;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.ui.Model;

@Controller
public class BookController {

  private BookRepository bookRepository;
  private BookInstanceRepository bookInstanceRepository;

  BookController(BookRepository bookRepository, BookInstanceRepository bookInstanceRepository) {
    this.bookRepository = bookRepository;
    this.bookInstanceRepository = bookInstanceRepository;
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
}
