package br.com.banco.domain.validations.conta.create;

import br.com.banco.application.DTOs.conta.CreateContaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCapacidadeMinimaNomeResponsavelCriarConta implements IValidacaoCriarConta{
    public void validar(CreateContaDTO createContaDTO){
        if(createContaDTO.nomeResponsavel().length() < 3){
            throw new ValidacaoException("Não foi possível cadastrar a conta no banco de dados.<br>Motivo: o campo " +
                    "nomeResponsável deve possuir, no mínimo, 3 caracteres. O campo nomeResponsavel = " +
                    createContaDTO.nomeResponsavel() + " possui " + createContaDTO.nomeResponsavel().length() +
                    " caracteres.",400);
        }
    }
}
