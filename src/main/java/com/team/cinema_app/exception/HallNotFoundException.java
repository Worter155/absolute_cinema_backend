package com.team.cinema_app.exception;

public class HallNotFoundException extends RuntimeException {
  public HallNotFoundException(String message) {
    super(message);
  }
}
