package com.myhomebe.exceptions;

public class NotFoundException extends RuntimeException {

  public NotFoundException(Long id) {
    super("Could not find requested object with id " + id);
  }
}
