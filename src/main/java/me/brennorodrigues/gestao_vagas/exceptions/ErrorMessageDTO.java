package me.brennorodrigues.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

    /**
     * DTOs normalmente sevem como interfaces que dizem o que é esperado para o formato dos dados que nós queremos trabalhar
     */
    
    private String message;
    private String field;
}
