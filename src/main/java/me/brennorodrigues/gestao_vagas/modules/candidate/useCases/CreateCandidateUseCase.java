package me.brennorodrigues.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.brennorodrigues.gestao_vagas.exceptions.UserFoundException;
import me.brennorodrigues.gestao_vagas.modules.candidate.entities.CandidateEntity;
import me.brennorodrigues.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    
    public CandidateEntity execute( CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });

        return this.candidateRepository.save(candidateEntity);
    }
    
}
