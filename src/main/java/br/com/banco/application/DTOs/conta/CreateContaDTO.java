package br.com.banco.application.DTOs.conta;

import jakarta.validation.constraints.NotBlank;

public record CreateContaDTO(
        String nomeResponsavel
) {}
