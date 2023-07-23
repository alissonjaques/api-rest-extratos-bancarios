package br.com.banco.domain.validations.usuario.post;

import br.com.banco.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteUsuarioComLoginInformado implements IValidacaoCriarUsuario {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void validar(CreateUsuarioDTO createUsuarioDTO){
        var login = createUsuarioDTO.login();
        var usuario = usuarioRepository.findByLogin(login);

        if(usuario != null){
            throw new ValidacaoException("Não foi possível criar o usuário.<br>Motivo: já existe usuário com login = " +
                    login + " cadastrado no sistema.",400);
        }
    }
}