package br.com.joaopedro.desafio_api.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.joaopedro.desafio_api.enums.StatusCurso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter @Setter
@Table(name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 @NotBlank(message = "O nome do curso é obrigatório.")
 @Column(nullable = false)
 private String name;

 @NotBlank(message = "A Categoria do curso é obrigatória.")
 @Column(nullable = false)
 private String category;

 @Column(nullable = false)
 private String teacherName = "Sem professor";

 @Enumerated(EnumType.STRING)
 @Column(name = "status", nullable = false)
 private StatusCurso status = StatusCurso.ATIVO;

 @CreationTimestamp
 private LocalDateTime createdAt;

 @UpdateTimestamp
 private LocalDateTime updatedAt;
}
