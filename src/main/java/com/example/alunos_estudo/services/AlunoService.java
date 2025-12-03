package com.example.alunos_estudo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.alunos_estudo.dto.AlunoRequest;
import com.example.alunos_estudo.dto.AlunoResponse;
import com.example.alunos_estudo.dto.MatriculaDTO;
import com.example.alunos_estudo.entities.Aluno;
import com.example.alunos_estudo.entities.Matricula;
import com.example.alunos_estudo.mappers.AlunoMapper;
import com.example.alunos_estudo.repositories.AlunoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@SuppressWarnings("null")
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoResponse salvar(AlunoRequest request) {
        Aluno aluno = alunoMapper.toEntity(request);
        alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listaTodos() {
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDTO> listaMatriculas(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
        return aluno.getMatriculas().stream().map(mat -> new MatriculaDTO(mat.getCodigoMatricula(), mat.getNomeCurso(), mat.getDataInicio())).toList();
    }

    public void remover(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("ID do aluno não encontrado!");
        }
        alunoRepository.deleteById(id);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
        aluno.setNome(request.nome());
        aluno.setTelefone(request.telefone());
        aluno.setDataNascimento(request.dataNascimento());

        for(MatriculaDTO mat : request.matriculas()) {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(mat.codigoMatricula());
            matricula.setDataInicio(mat.dataInicio());
            matricula.setNomeCurso(mat.nomeCurso());
            aluno.getMatriculas().add(matricula);
        }

        return alunoMapper.toResponse(alunoRepository.save(aluno));
    }
}
