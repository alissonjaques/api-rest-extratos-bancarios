package br.com.banco.domain.validations.conta.update;

import br.com.banco.application.DTOs.conta.CreateContaDTO;
import br.com.banco.application.DTOs.conta.UpdateContaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCapacidadeMaximaNomeResponsavelEditarConta implements IValidacaoEditarConta {
    public void validar(UpdateContaDTO updateContaDTO){
        if(updateContaDTO.nomeResponsavel().length() > 50){
            throw new ValidacaoException("Não foi possível editar a conta no banco de dados.<br>Motivo: o campo " +
                    "nomeResponsável deve possuir, no máximo, 50 caracteres. O campo nomeResponsavel = " +
                    updateContaDTO.nomeResponsavel() + " possui " + updateContaDTO.nomeResponsavel().length() +
                    " caracteres.",400);
        }
    }
}
