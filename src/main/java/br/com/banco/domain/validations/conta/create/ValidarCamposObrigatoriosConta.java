package br.com.banco.domain.validations.conta.create;

import br.com.banco.application.DTOs.conta.CreateContaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCamposObrigatoriosConta implements IValidacaoCriarConta {
    public void validar(CreateContaDTO createContaDTO){
        if(createContaDTO.nomeResponsavel() == null || createContaDTO.nomeResponsavel().isBlank()){
            throw new ValidacaoException("Não foi possível cadastrar a conta no banco de dados.<br>Motivo: o campo " +
                    "nomeResponsável é obrigatório",400);
        }
    }
}