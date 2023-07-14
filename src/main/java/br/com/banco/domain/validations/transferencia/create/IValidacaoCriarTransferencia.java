package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;

public interface IValidacaoCriarTransferencia {
    void validar(CreateTransferenciaDTO createTransferenciaDTO);
}
