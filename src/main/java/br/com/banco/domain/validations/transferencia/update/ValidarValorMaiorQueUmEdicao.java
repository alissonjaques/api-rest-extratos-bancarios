package br.com.banco.domain.validations.transferencia.update;

import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarValorMaiorQueUmEdicao implements IValidacaoEditarTransferencia {
    public void validar(UpdateTransferenciaDTO updateTransferenciaDTO){
        if(updateTransferenciaDTO.valor() < 1){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: " +
                    "o valor da transferência deve ser positivo e maior do que zero.",400);
        }
    }
}
