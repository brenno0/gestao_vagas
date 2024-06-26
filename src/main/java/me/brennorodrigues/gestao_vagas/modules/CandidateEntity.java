package me.brennorodrigues.gestao_vagas.modules;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity(name = "cadidate")
public class CandidateEntity {

    /**
     * Aqui ela só definiu uma espécie de middlware que vai funcionar como validação para os nossos campos
     *  dizendo do formato obrgatório de cada um e também o tipo esperado 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não pode conter espaços" )
    private String username;
    
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;
    
    @Length(min = 10, max = 100, message = "O campo [password] deve conter uma senha entre 10 e 100 caracteres.")
    private String password;
    
    private String description;
    private String curriculum;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
