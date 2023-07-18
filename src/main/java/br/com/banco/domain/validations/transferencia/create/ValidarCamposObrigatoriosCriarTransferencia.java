package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCamposObrigatoriosCriarTransferencia implements IValidacaoCriarTransferencia {
    public void validar(CreateTransferenciaDTO createTransferenciaDTO){
        if(createTransferenciaDTO.dataTransferencia() == null){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "dataTransferencia é obrigatório",400);
        }

        if(createTransferenciaDTO.valor() == null){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "valor é obrigatório",400);
        }

        if(createTransferenciaDTO.tipo() == null){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "tipo é obrigatório",400);
        }

        if(createTransferenciaDTO.nomeOperadorTransacao() == null || createTransferenciaDTO.nomeOperadorTransacao().isBlank() ){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "nomeOperadorTransacao é obrigatório",400);
        }

        if(createTransferenciaDTO.contaId() == null){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: o campo " +
                    "contaId é obrigatório",400);
        }
    }
}