package br.com.joaopedro.desafio_api.repository;

import br.com.joaopedro.desafio_api.entities.Curso;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, UUID> {
 List<Curso> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase (String name, String category);
 
} 