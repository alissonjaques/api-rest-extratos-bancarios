package br.com.banco.domain.validations.conta.update;

import br.com.banco.application.DTOs.conta.UpdateContaDTO;

public interface IValidacaoEditarConta {
    void validar(UpdateContaDTO updateContaDTO);
}
