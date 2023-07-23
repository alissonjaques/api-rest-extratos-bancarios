package br.com.banco.domain.validations.usuario.post;

import br.com.banco.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;
@Component()
public class ValidarCamposObrigatoriosUsuario implements IValidacaoCriarUsuario {
    public void validar(CreateUsuarioDTO createUsuarioDTO){
        if(createUsuarioDTO.login() == null || createUsuarioDTO.login().isBlank() ){
            throw new ValidacaoException("Não foi possível criar o usuário.<br>Motivo: o campo login é obrigatório.",
                    400);
        }

        if(createUsuarioDTO.senha() == null || createUsuarioDTO.senha().isBlank()){
            throw new ValidacaoException("Não foi possível criar o usuário.<br>Motivo: o campo senha é obrigatório.",
                    400);
        }
    }
}