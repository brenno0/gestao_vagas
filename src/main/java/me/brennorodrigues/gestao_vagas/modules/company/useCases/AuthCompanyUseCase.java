package me.brennorodrigues.gestao_vagas.modules.company.useCases;


import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import me.brennorodrigues.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import me.brennorodrigues.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import me.brennorodrigues.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;
    
    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("Nome do usuário ou senha incorretos.");
        });
        var expiresAt = Instant.now().plus(Duration.ofHours(2));

        Boolean passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException("Nome do usuário ou senha incorretos.");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var jwt = JWT.create()
        .withIssuer("javagas")
        .withClaim("roles", Arrays.asList("COMPANY"))
        .withExpiresAt(expiresAt)
        .withSubject(company.getId().toString()).sign(algorithm);

        return AuthCompanyResponseDTO.builder().access_token(jwt).expires_at(expiresAt.toEpochMilli()).build();
    }
}
