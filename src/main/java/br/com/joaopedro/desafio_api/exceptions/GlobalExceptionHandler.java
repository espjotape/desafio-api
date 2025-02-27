package br.com.joaopedro.desafio_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
 
 @ExceptionHandler(CursoNotFoundException.class)
 public ResponseEntity<String> handleCursoNotFound(CursoNotFoundException ex) {
  return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
 }

 // Trata a exceção InvalidCourseDataException
 @ExceptionHandler(InvalidCourseDataException.class)
 public ResponseEntity<String> handleInvalidCourseData(InvalidCourseDataException ex) {
  return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
 }

 // Trata exceção de ID inválido
 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handlUUIDFormatError(MethodArgumentTypeMismatchException ex) {
   return ResponseEntity.badRequest().body("ID inválido. O Formato esperado é UUID.");
  }

}