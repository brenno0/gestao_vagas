package me.brennorodrigues.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.brennorodrigues.gestao_vagas.modules.company.entities.JobEntity;
import me.brennorodrigues.gestao_vagas.modules.company.useCases.CreateJobUseCase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("")
    public @Valid ResponseEntity<Object> postMethodName(@Valid @RequestBody JobEntity jobEntity) {
        try {
            var response = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(response) ;
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
