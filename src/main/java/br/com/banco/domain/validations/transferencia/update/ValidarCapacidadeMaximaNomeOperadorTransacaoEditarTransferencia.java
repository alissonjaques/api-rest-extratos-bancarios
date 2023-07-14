package br.com.banco.domain.validations.transferencia.update;

import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCapacidadeMaximaNomeOperadorTransacaoEditarTransferencia implements IValidacaoEditarTransferencia {
    public void validar(UpdateTransferenciaDTO updateTransferenciaDTO){
        if(updateTransferenciaDTO.nomeOperadorTransacao().length() > 50){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "nomeOperadorTransacao deve possuir, no máximo, 50 caracteres. O campo nomeOperadorTransacao = " +
                    updateTransferenciaDTO.nomeOperadorTransacao() + " possui " + updateTransferenciaDTO.nomeOperadorTransacao().length() +
                    " caracteres.",400);
        }
    }
}
