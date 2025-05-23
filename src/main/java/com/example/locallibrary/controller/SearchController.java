package com.example.locallibrary.controller;

import com.example.locallibrary.model.Book;
import com.example.locallibrary.repository.SearchRepository;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class SearchController {

  private SearchRepository searchRepository;

  SearchController(SearchRepository searchRepository) {
    this.searchRepository = searchRepository;
  }

  @GetMapping("/search")
  public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
    
    model.addAttribute("keyword", keyword);
    
    // Searching
    if (keyword != null) {
      List<Book> results; 
      results = searchRepository.findByTitleContainingIgnoreCase(keyword);
      
      model.addAttribute("results", results);
    }

    return "search_form"; 
  }
}
