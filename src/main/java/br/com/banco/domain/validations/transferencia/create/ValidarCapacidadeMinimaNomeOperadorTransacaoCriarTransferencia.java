package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCapacidadeMinimaNomeOperadorTransacaoCriarTransferencia implements IValidacaoCriarTransferencia {
    public void validar(CreateTransferenciaDTO createTransferenciaDTO){
        if(createTransferenciaDTO.nomeOperadorTransacao().length() < 3){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "nomeOperadorTransacao deve possuir, no mínimo, 3 caracteres. O campo nomeOperadorTransacao = " +
                    createTransferenciaDTO.nomeOperadorTransacao() + " possui " + createTransferenciaDTO.nomeOperadorTransacao().length() +
                    " caracteres.",400);
        }
    }
}
