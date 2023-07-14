package br.com.banco.domain.validations.transferencia.update;

import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;

public interface IValidacaoEditarTransferencia {
    void validar(UpdateTransferenciaDTO updateTransferenciaDTO);
}
