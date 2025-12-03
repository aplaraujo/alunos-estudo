package com.example.alunos_estudo.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.alunos_estudo.dto.AlunoRequest;
import com.example.alunos_estudo.dto.AlunoResponse;
import com.example.alunos_estudo.dto.MatriculaDTO;
import com.example.alunos_estudo.entities.Aluno;
import com.example.alunos_estudo.entities.Matricula;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.nome());
        aluno.setTelefone(alunoRequest.telefone());
        aluno.setDataNascimento(alunoRequest.dataNascimento());

        List<Matricula> matriculas = alunoRequest.matriculas().stream().map(mat -> {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(mat.codigoMatricula());
            matricula.setDataInicio(mat.dataInicio());
            matricula.setNomeCurso(mat.nomeCurso());
            matricula.setAluno(aluno);
            return matricula;
        }).toList();
        aluno.setMatriculas(matriculas);

        return aluno;
    }

    public AlunoResponse toResponse(Aluno aluno) {
        List<MatriculaDTO> dto = aluno.getMatriculas().stream().map(mat -> 
            new MatriculaDTO(mat.getCodigoMatricula(), mat.getNomeCurso(), mat.getDataInicio())
        ).toList();
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), dto);
    }
}
