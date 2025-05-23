package com.example.locallibrary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.locallibrary.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
  List<Book> findByAuthorId(Long id);

  List<Book> findByGenreId(Long id);

  int countByAuthorId(Long id);
  
  int countByGenreId(Long id);
}

