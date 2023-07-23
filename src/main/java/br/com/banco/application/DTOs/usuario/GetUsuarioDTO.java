package br.com.banco.application.DTOs.usuario;

import br.com.banco.domain.model.Usuario;
public record GetUsuarioDTO(Long idUsuario, String login, String senha) {
    public GetUsuarioDTO(Usuario usuario) {
        this(usuario.getIdUsuario(), usuario.getLogin(),usuario.getSenha());
    }
}
