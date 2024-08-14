package me.brennorodrigues.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.brennorodrigues.gestao_vagas.modules.candidate.dto.CandidateProfileResponseDTO;
import me.brennorodrigues.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CandidateProfileUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    public CandidateProfileResponseDTO execute(UUID candidateId) {

        
        var candidate = this.candidateRepository.findById(candidateId)
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário não encontrado");
        });
        
        var  candidateProfileDTO = CandidateProfileResponseDTO.builder();
        
        return candidateProfileDTO
        .description(candidate.getDescription())
        .name(candidate.getName())
        .email(candidate.getEmail())
        .username(candidate.getUsername())
        .id(candidate.getId())
        .build();
    }
    
}
