package com.example.alunos_estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alunos_estudo.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

}
