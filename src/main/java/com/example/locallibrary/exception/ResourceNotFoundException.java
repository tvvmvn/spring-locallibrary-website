package com.example.locallibrary.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(Long id) {
    super("Could not find resource in id = " + id);
  }
}
