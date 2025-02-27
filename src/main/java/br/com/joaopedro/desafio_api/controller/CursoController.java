package br.com.joaopedro.desafio_api.controller;

import br.com.joaopedro.desafio_api.entities.Curso;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  return ResponseEntity.ok(cursoUseCase.criarCurso(curso));
 } 

 @GetMapping
 public ResponseEntity<List<Curso>> listarCursos (
  @RequestParam(required = false) String name,
  @RequestParam(required = false) String category ) {

   List<Curso> cursos = cursoUseCase.listarCursos(name, category);
   return ResponseEntity.ok(cursos);

  }

  @PutMapping("/{id}")
  public ResponseEntity<Curso> atualizarCurso(@PathVariable UUID id, @RequestBody Curso curso) {
   try {
    return cursoUseCase.atualizarCurso(id, curso)
     .map(ResponseEntity::ok)
     .orElse(ResponseEntity.notFound().build());
   } catch (Exception e) {
    e.printStackTrace();
    return ResponseEntity.status(500).body(null);
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
}
