package com.example.alunos_estudo.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Matricula extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String codigoMatricula;
    private String nomeCurso;
    private LocalDate dataInicio;

    @ManyToOne
    @JoinColumn(name="aluno_id")
    private Aluno aluno;
}
