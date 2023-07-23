package br.com.banco.domain.validations.usuario.update;

import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component()
public class ValidarExisteUsuarioComLoginInformadoEdicao implements IValidacaoEditarUsuario {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void validar(UpdateUsuarioDTO updateUsuarioDTO){
        var login = updateUsuarioDTO.login();
        var usuario = usuarioRepository.findByLoginUsuario(login);

        if(usuario != null && !Objects.equals(updateUsuarioDTO.idUsuario(), usuario.getIdUsuario())){
            throw new ValidacaoException("Não foi possível editar o usuário.<br>Motivo: já existe usuário com login = " +
                    login + " cadastrado no sistema.",400);
        }
    }
}