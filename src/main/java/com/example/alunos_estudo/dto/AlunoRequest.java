package com.example.alunos_estudo.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AlunoRequest(String nome, String telefone, LocalDate dataNascimento, @NotNull List<@Valid MatriculaDTO> matriculas) {

}
