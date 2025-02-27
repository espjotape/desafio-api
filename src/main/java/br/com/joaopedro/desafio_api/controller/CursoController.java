package br.com.joaopedro.desafio_api.controller;

import br.com.joaopedro.desafio_api.entities.Curso;
import br.com.joaopedro.desafio_api.exceptions.CursoNotFoundException;
import br.com.joaopedro.desafio_api.exceptions.InvalidCourseDataException;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedro.desafio_api.useCases.CursoUseCase;


@RestController
@RequestMapping("/cursos")
public class CursoController {

 @Autowired
 private CursoUseCase cursoUseCase;

 public CursoController(CursoUseCase cursoUseCase) {
  this.cursoUseCase = cursoUseCase;
 }

 @PostMapping
 public ResponseEntity<Curso> criarCurso (@RequestBody Curso curso) {
  if(curso.getName() == null || curso.getCategory() == null) {
    throw new InvalidCourseDataException("O nome e a categoria do curso são obrigatórios.");
  }
  // Lógica para salvar o curso (exemplo)
  Curso novoCurso = cursoUseCase.criarCurso(curso);

  return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
 } 

 @GetMapping
 public ResponseEntity<List<Curso>> listarCursos (
  @RequestParam(required = false) String name,
  @RequestParam(required = false) String category ) {

   List<Curso> cursos = cursoUseCase.listarCursos(name, category);
   return ResponseEntity.ok(cursos);

  }

 @PutMapping("/{id}")
 public ResponseEntity<?> atualizarCurso(@PathVariable UUID id,@RequestBody Curso curso) {
  try {
    return cursoUseCase.atualizarCurso(id, curso)
     .map(ResponseEntity::ok)
     .orElseThrow(() -> new CursoNotFoundException("Curso não encontrado com o ID: " + id));
   } catch(CursoNotFoundException e ) {
    return ResponseEntity.status(404).body(e.getMessage());
   } catch (Exception e) {
    e.printStackTrace();
    return ResponseEntity.status(500).body("Erro interno no servidor.");
   }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarCurso(@PathVariable UUID id) {
   try {
    cursoUseCase.delete(id);
    return ResponseEntity.ok("Curso deletado com sucesso.");
   } catch (Exception e) {
     e.printStackTrace();
     return ResponseEntity.status(500).body("Erro ao deletar o curso.");
   }
  }

  @PatchMapping("/{id}/active")
  public ResponseEntity<String> checkCurso(@PathVariable UUID id) {
   try {
    cursoUseCase.checkCurso(id);
    return ResponseEntity.ok("Curso ativado/desativado com sucesso.");
   } catch (Exception e) {
     e.printStackTrace();
     return ResponseEntity.status(500).body("Erro ao ativar/desativar o curso.");
   }
 }
}