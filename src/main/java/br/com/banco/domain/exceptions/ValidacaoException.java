package br.com.banco.domain.exceptions;

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
