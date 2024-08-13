package me.brennorodrigues.gestao_vagas.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ForeignIdNotFoundExeption extends DataIntegrityViolationException{
    public ForeignIdNotFoundExeption() {
        super("Empresa inválida ou não encontrada, por favor tente novamente com um identificador válido.");
    }
}
