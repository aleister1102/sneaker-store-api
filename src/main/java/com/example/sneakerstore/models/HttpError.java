package com.example.sneakerstore.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;


@Data
@Builder
public class HttpError {
  HttpStatusCode statusCode;
  String message;
}
