package br.com.banco.domain.validations.usuario.post;

import br.com.banco.application.DTOs.usuario.CreateUsuarioDTO;
public interface IValidacaoCriarUsuario {
    void validar(CreateUsuarioDTO createUsuarioDTO);
}
