package com.example.locallibrary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.locallibrary.model.BookInstance;

public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {
    
  List<BookInstance> findByBookId(Long id);
  
  int countByBookId(Long id);
}

