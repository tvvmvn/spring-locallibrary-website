package com.example.locallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.locallibrary.model.Book;
import java.util.List;

public interface SearchRepository extends JpaRepository<Book, Long> {
  List<Book> findByTitleContainingIgnoreCase(String keyword);
}

