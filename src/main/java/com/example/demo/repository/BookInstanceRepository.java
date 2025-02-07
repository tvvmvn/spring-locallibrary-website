package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BookInstance;

public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {
    List<BookInstance> findByBookId(Long id);
    int countByBookId(Long id);
}

