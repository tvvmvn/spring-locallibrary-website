package com.example.demo.controller;

import com.example.demo.enums.Status;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BookInstance;
import com.example.demo.model.Book;
import com.example.demo.repository.BookInstanceRepository;
import com.example.demo.repository.BookRepository;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class BookInstanceController {

  private BookInstanceRepository bookInstanceRepository;
  private BookRepository bookRepository;

  BookInstanceController(BookInstanceRepository bookInstanceRepository, BookRepository bookRepository) {
    this.bookInstanceRepository = bookInstanceRepository;
    this.bookRepository = bookRepository;
  }

  // Get all bookInstances
  @GetMapping("/bookinstances")
  public String getAll(Model model) {
    List<BookInstance> bookInstances = bookInstanceRepository.findAll();

    model.addAttribute("bookInstances", bookInstances);

    return "bookinstance_list";
  }

  // Get a bookInstance
  @GetMapping("/bookinstance/{id}")
  public String getOne(@PathVariable("id") Long id, Model model) {
    
    BookInstance bookInstance = bookInstanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("bookInstance", bookInstance);

    return "bookinstance_detail";
  }

  // Save a bookInstance
  @GetMapping("/bookinstance/create")
  public String greetingForm(Model model) {

    BookInstance bookInstance = new BookInstance();
    
    model.addAttribute("bookInstance", bookInstance);
    
    List<Book> books = bookRepository.findAll();

    model.addAttribute("books", books);

    model.addAttribute("status", Status.values());

    return "bookinstance_form";
  }

  @PostMapping("/bookinstance/create")
  public String greetingSubmit(@Valid BookInstance bookInstance, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {

      List<Book> books = bookRepository.findAll();

      model.addAttribute("books", books);
  
      model.addAttribute("status", Status.values());
      
      return "bookinstance_form";
    }

    bookInstanceRepository.save(bookInstance);

    return "redirect:/bookinstances";
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

  // Delete a bookInstance
  @GetMapping("/bookinstance/{id}/delete")
  public String deleteGet(@PathVariable("id") Long id, Model model) {

    BookInstance bookInstance = bookInstanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    model.addAttribute("bookInstance", bookInstance);

    return "bookinstance_delete";
  }

  @PostMapping("/bookinstance/{id}/delete")
  public String deletePost(@PathVariable("id") Long id) {

    BookInstance bookInstance = bookInstanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

    bookInstanceRepository.deleteById(id);

    return "redirect:/bookinstances";
  }
}
