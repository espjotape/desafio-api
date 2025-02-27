package br.com.joaopedro.desafio_api.exceptions;

public class CursoNotFoundException extends RuntimeException {
 
 public CursoNotFoundException(String message) {
  super(message);
 }

 public CursoNotFoundException(String message, Throwable cause) {
  super(message, cause);
 }
 
}
