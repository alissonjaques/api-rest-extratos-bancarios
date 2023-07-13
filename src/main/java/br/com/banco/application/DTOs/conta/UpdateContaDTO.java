package br.com.banco.application.DTOs.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateContaDTO(
        Long idConta,
        String nomeResponsavel) {
}