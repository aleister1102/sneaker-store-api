package com.example.sneakerstore.controllers;

import com.example.sneakerstore.models.HttpError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class HttpErrorHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<HttpError> handleValidationException(MethodArgumentNotValidException e) {
    HttpError httpError = HttpError.builder()
            .statusCode(HttpStatus.BAD_REQUEST)
            .message(e.getMessage())
            .build();
    return ResponseEntity.badRequest().body(httpError);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<HttpError> handleClientException(HttpClientErrorException e) {
    HttpError httpError = HttpError.builder()
            .statusCode(e.getStatusCode())
            .message(e.getMessage())
            .build();
    return ResponseEntity.status(e.getStatusCode()).body(httpError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<HttpError> handleException(Exception e) {
    HttpError httpError = HttpError.builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpError);
  }
}
