package br.com.joaopedro.desafio_api.useCases;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import br.com.joaopedro.desafio_api.entities.Curso;
import br.com.joaopedro.desafio_api.enums.StatusCurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaopedro.desafio_api.repository.CursoRepository;

@Service
public class CursoUseCase {
 
 @Autowired
 private CursoRepository cursoRepository;

 public CursoUseCase(CursoRepository cursoRepository) {
  this.cursoRepository = cursoRepository;
 }

 public Curso criarCurso(Curso curso) {
  if(curso.getTeacherName() == null || curso.getTeacherName().trim().isEmpty()){
    curso.setTeacherName("Sem professor");
  }
  return cursoRepository.save(curso);
 }

 public List<Curso> listarCursos(String name, String category, String teacherName) {
  if (name != null || category != null ) {
   return cursoRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(name, category);
  }

  return cursoRepository.findAll();
 }

 public Optional<Curso> atualizarCurso(UUID id, Curso cursoAtualizado) {
  return cursoRepository.findById(id)
  .map(curso -> {
   if (cursoAtualizado.getName() != null) {
    curso.setName(cursoAtualizado.getName());
   }

   if (cursoAtualizado.getCategory() != null) {
    curso.setCategory(cursoAtualizado.getCategory());
   }

   if (cursoAtualizado.getTeacherName() == null || cursoAtualizado.getTeacherName().trim().isEmpty()) {
    curso.setTeacherName("Sem professor");
    } else {
      curso.setTeacherName(cursoAtualizado.getTeacherName());
    }

  return cursoRepository.save(curso);
  });
 }

 public void delete(UUID id) {
  cursoRepository.deleteById(id);
 }

public Optional<Curso> checkCurso(UUID id) {
  return cursoRepository.findById(id).map(curso -> {
  // Alterna entre ATIVO e INATIVO
    if (curso.getStatus() == StatusCurso.ATIVO) {
      curso.setStatus(StatusCurso.INATIVO);
    } else {
      curso.setStatus(StatusCurso.ATIVO);
    }
    return cursoRepository.save(curso);
    });
}

  public Optional<Curso> buscarCursoPorId(UUID id) {
    return cursoRepository.findById(id);
  }

}
