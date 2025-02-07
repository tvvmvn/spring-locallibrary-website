package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.Genre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

