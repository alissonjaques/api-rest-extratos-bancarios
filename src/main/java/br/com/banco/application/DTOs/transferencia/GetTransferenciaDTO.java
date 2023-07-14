package br.com.banco.application.DTOs.transferencia;

import br.com.banco.domain.enums.Tipo;
import br.com.banco.domain.model.Transferencia;

import java.time.LocalDateTime;

public record GetTransferenciaDTO(
        Long idTransferencia,
        LocalDateTime dataTransferencia,
        Double valor,
        Tipo tipo,
        String nomeOperadorTransacao,
        Long contaId
) {
    public GetTransferenciaDTO(Transferencia transferencia){
        this(
                transferencia.getIdTransferencia(),
                transferencia.getDataTransferencia(),
                transferencia.getValor(),
                transferencia.getTipo(),
                transferencia.getNomeOperadorTransacao(),
                transferencia.getConta().getIdConta());
    }
}