package br.com.banco.domain.validations.transferencia.relatorios;

import br.com.banco.application.DTOs.transferencia.GetRelatorioTransferenciaDTO;

public interface IValidacaoRelatorioTransferencia {
    void validar(GetRelatorioTransferenciaDTO getRelatorioTransferenciaDTO);
}
