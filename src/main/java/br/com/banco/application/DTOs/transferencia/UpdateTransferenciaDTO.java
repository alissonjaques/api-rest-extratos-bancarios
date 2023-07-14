package br.com.banco.application.DTOs.transferencia;

import br.com.banco.domain.enums.Tipo;

import java.time.LocalDateTime;

public record UpdateTransferenciaDTO(
        Long idTransferencia,
        LocalDateTime dataTransferencia,
        Double valor,
        Tipo tipo,
        String nomeOperadorTransacao,
        Long contaId
) {}