package br.com.banco.application.DTOs.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateContaDTO(
        @NotNull
        Long idConta,
        @NotBlank(message = "{nomeResponsavel.obrigatorio}")
        String nomeResponsavel) {
}