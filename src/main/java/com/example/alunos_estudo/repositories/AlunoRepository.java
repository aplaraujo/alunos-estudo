package com.example.alunos_estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alunos_estudo.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
