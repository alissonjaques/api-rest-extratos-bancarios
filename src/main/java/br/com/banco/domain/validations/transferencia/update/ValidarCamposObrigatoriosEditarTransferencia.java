package br.com.banco.domain.validations.transferencia.update;

import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCamposObrigatoriosEditarTransferencia implements IValidacaoEditarTransferencia {
    public void validar(UpdateTransferenciaDTO updateTransferenciaDTO){
        if(updateTransferenciaDTO.idTransferencia() == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "idTransferencia é obrigatório",400);
        }

        if(updateTransferenciaDTO.dataTransferencia() == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "dataTransferencia é obrigatório",400);
        }

        if(updateTransferenciaDTO.dataTransferencia() == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "dataTransferencia é obrigatório",400);
        }

        if(updateTransferenciaDTO.valor() == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "valor é obrigatório",400);
        }

        if(updateTransferenciaDTO.nomeOperadorTransacao() == null || updateTransferenciaDTO.nomeOperadorTransacao().isBlank() ){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "nomeOperadorTransacao é obrigatório",400);
        }

        if(updateTransferenciaDTO.contaId() == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: o campo " +
                    "contaId é obrigatório",400);
        }
    }
}