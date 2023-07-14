package br.com.banco.application.DTOs.conta;

public record UpdateContaDTO(
        Long idConta,
        String nomeResponsavel) {
}