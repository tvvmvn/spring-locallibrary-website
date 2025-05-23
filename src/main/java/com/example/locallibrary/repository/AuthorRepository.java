package com.example.locallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.locallibrary.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

