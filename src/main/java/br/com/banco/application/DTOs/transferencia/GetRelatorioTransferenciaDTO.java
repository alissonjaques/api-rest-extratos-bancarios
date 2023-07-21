package br.com.banco.application.DTOs.transferencia;

import java.sql.Date;

public record GetRelatorioTransferenciaDTO(
        Date dataInicio,
        Date dataFim){
}
