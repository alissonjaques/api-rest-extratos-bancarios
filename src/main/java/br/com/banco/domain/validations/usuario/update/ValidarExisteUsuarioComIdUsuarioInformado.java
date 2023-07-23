package br.com.banco.domain.validations.usuario.update;

import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteUsuarioComIdUsuarioInformado implements IValidacaoEditarUsuario {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void validar(UpdateUsuarioDTO updateUsuarioDTO){
        var idUsuario = updateUsuarioDTO.idUsuario();
        var usuario = usuarioRepository.findByIdUsuario(idUsuario);

        if(usuario == null){
            throw new ValidacaoException("Não foi possível editar o usuário.<br>Motivo: não existe usuário com idUsuaro = " +
                    idUsuario + " cadastrado no sistema.",400);
        }
    }
}