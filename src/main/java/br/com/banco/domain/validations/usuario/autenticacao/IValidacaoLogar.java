package br.com.banco.domain.validations.usuario.autenticacao;

import br.com.banco.application.DTOs.usuario.AutenticacaoDTO;

public interface IValidacaoLogar {
    void validar(AutenticacaoDTO autenticacaoDTO);
}
