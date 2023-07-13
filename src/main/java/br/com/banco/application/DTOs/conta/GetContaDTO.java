package br.com.banco.application.DTOs.conta;

import br.com.banco.domain.model.Conta;

public record GetContaDTO(Long idConta, String nomeResponsavel) {
    public GetContaDTO(Conta conta){
        this(conta.getIdConta(), conta.getNomeResponsavel());
    }
}