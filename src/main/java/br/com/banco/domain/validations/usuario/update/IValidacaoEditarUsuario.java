package br.com.banco.domain.validations.usuario.update;

import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
public interface IValidacaoEditarUsuario {
    void validar(UpdateUsuarioDTO updateUsuarioDTO);
}
