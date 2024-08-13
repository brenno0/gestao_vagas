package me.brennorodrigues.gestao_vagas.modules.company.controllers;


import jakarta.validation.Valid;
import me.brennorodrigues.gestao_vagas.modules.company.entities.CompanyEntity;
import me.brennorodrigues.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("")
    public @Valid ResponseEntity<Object> create( @Valid @RequestBody CompanyEntity companyEntity) {
        try {

            var result = this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok().body(result);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public String testGet() {
        return "Testando rotas";
    }
    


}
