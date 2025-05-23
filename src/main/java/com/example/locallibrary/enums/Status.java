package com.example.locallibrary.enums;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
  
  MAINTENANCE("Maintenance"),
  AVAILABLE("Available"),
  LOANED("Loaned"),
  RESERVED("Reserved");

  private final String label;

  Status(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return this.label;
  }
}
