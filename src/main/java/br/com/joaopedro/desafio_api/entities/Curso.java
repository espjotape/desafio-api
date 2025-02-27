package br.com.joaopedro.desafio_api.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter @Setter
@Table(name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private int id;

 @Column(nullable = false)
 private String name;
 
 @Column(nullable = false)
 private String category;

 private boolean active = true;

 @CreationTimestamp
 private LocalDateTime createdAt;

 @UpdateTimestamp
 private LocalDateTime updatedAt;
}
