package br.com.banco.infra.exception;

import br.com.banco.application.DTOs.exceptions.ExceptionDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(ex.getMessage(), ex.getStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErroParser(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(ex.getMessage(),400));
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(),erro.getDefaultMessage());
        }
    }
}
