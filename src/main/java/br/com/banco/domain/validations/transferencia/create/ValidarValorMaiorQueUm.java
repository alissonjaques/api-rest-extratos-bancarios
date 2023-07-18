package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarValorMaiorQueUm implements IValidacaoCriarTransferencia {
    public void validar(CreateTransferenciaDTO createTransferenciaDTO){
        if(createTransferenciaDTO.valor() < 1){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: " +
                    "o valor da transferência deve ser positivo e maior do que zero.",400);
        }
    }
}
