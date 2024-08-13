package me.brennorodrigues.gestao_vagas.modules.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.brennorodrigues.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import me.brennorodrigues.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;

@RequestMapping("/auth")
@RestController
public class AuthCompanyController {


    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;
    
    @PostMapping("/company")
    public ResponseEntity<Object> companyAuthentication(@RequestBody AuthCompanyDTO authCompanyDto) throws AuthenticationException {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
