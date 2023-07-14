package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCapacidadeMaximaNomeOperadorTransacaoCriarTransferencia implements IValidacaoCriarTransferencia {
    public void validar(CreateTransferenciaDTO createTransferenciaDTO){
        if(createTransferenciaDTO.nomeOperadorTransacao().length() > 50){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "nomeOperadorTransacao deve possuir, no máximo, 50 caracteres. O campo nomeOperadorTransacao = " +
                    createTransferenciaDTO.nomeOperadorTransacao() + " possui " + createTransferenciaDTO.nomeOperadorTransacao().length() +
                    " caracteres.",400);
        }
    }
}
