package br.com.banco.domain.validations.usuario.update;

import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;
@Component()
public class ValidarCamposObrigatoriosUsuarioEdicao implements IValidacaoEditarUsuario {
    public void validar(UpdateUsuarioDTO updateUsuarioDTO){
        if(updateUsuarioDTO.idUsuario() == null){
            throw new ValidacaoException("Não foi possível editar o usuário.<br>Motivo: o campo idUsuario é obrigatório.",
                    400);
        }

        if(updateUsuarioDTO.login() == null || updateUsuarioDTO.login().isBlank() ){
            throw new ValidacaoException("Não foi possível editar o usuário.<br>Motivo: o campo login é obrigatório.",
                    400);
        }

        if(updateUsuarioDTO.senha() == null || updateUsuarioDTO.senha().isBlank()){
            throw new ValidacaoException("Não foi possível editar o usuário.<br>Motivo: o campo senha é obrigatório.",
                    400);
        }
    }
}