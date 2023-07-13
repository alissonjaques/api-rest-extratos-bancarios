package br.com.banco.domain.validations.conta.delete;

import br.com.banco.application.DTOs.conta.CreateContaDTO;

public interface IValidacaoDeletarConta {
    void validar(Long idConta);
}
