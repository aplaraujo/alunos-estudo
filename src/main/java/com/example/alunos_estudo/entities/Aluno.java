package com.example.alunos_estudo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy="aluno", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Matricula> matriculas = new ArrayList<>();
}
