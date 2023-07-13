package br.com.banco.domain.validations.conta.create;

import br.com.banco.application.DTOs.conta.CreateContaDTO;

public interface IValidacaoCriarConta {
    void validar(CreateContaDTO createContaDTO);
}
