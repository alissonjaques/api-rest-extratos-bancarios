package br.com.banco.domain.validations.conta.update;

import br.com.banco.application.DTOs.conta.UpdateContaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCamposObrigatoriosEditarConta implements IValidacaoEditarConta {
    public void validar(UpdateContaDTO updateContaDTO){
        if(updateContaDTO.nomeResponsavel() == null || updateContaDTO.nomeResponsavel().isBlank()){
            throw new ValidacaoException("Não foi possível editar a conta no banco de dados.<br>Motivo: o campo " +
                    "nomeResponsável é obrigatório",400);
        }

        if(updateContaDTO.idConta() == null){
            throw new ValidacaoException("Não foi possível editar a conta no banco de dados.<br>Motivo: o campo " +
                    "idConta é obrigatório",400);
        }
    }
}