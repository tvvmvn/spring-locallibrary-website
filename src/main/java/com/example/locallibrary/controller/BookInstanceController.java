package com.example.locallibrary.controller;

import com.example.locallibrary.enums.Status;
import com.example.locallibrary.exception.ResourceNotFoundException;
import com.example.locallibrary.model.BookInstance;
import com.example.locallibrary.model.Book;
import com.example.locallibrary.repository.BookInstanceRepository;
import com.example.locallibrary.repository.BookRepository;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
public class BookInstanceController {

  private BookInstanceRepository bookInstanceRepository;
  private BookRepository bookRepository;

  BookInstanceController(BookInstanceRepository bookInstanceRepository, BookRepository bookRepository) {
    this.bookInstanceRepository = bookInstanceRepository;
    this.bookRepository = bookRepository;
  }

  // Get a bookInstance
  @GetMapping("/bookinstance/{id}")
  public String getOne(@PathVariable("id") Long id, Model model) {
    
    BookInstance bookInstance = bookInstanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("bookInstance", bookInstance);

    return "bookinstance_detail";
  }

  // Update a bookInstance
  @GetMapping("/bookinstance/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {

    BookInstance bookInstance = bookInstanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));
        
    model.addAttribute("bookInstance", bookInstance);
    
    List<Book> books = bookRepository.findAll();

    model.addAttribute("books", books);

    model.addAttribute("status", Status.values());

    return "bookinstance_form";
  }

  @PostMapping("/bookinstance/save")
  public String greetingSubmit(@Valid BookInstance bookInstance) {

    bookInstanceRepository.save(bookInstance);

    return "redirect:/bookinstances";
  }
}
