package br.com.banco.domain.exceptions;

import br.com.banco.application.DTOs.exceptions.ExceptionDTO;

public class ValidacaoException extends RuntimeException {
    private Integer status;
    public ValidacaoException(String mensagem, Integer status) {
        super(mensagem);
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}
