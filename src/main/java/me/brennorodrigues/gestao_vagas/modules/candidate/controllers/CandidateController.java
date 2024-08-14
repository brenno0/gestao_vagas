package me.brennorodrigues.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import me.brennorodrigues.gestao_vagas.modules.candidate.entities.CandidateEntity;
import me.brennorodrigues.gestao_vagas.modules.candidate.useCases.CandidateProfileUseCase;
import me.brennorodrigues.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private CandidateProfileUseCase candidateProfileUseCase;

    @PostMapping("")
    public @Valid ResponseEntity<Object> create( @Valid @RequestBody CandidateEntity candidateEntity) {
        try {

            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> list(@Valid HttpServletRequest request){
        try {
            var candidateId = request.getAttribute("candidate_id");
            var data = this.candidateProfileUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(data);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
