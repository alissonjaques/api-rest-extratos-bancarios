package br.com.banco.domain.validations.usuario.update;

import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component()
public class ValidarLoginEdicao implements IValidacaoEditarUsuario {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public void validar(UpdateUsuarioDTO updateUsuarioDTO){
        String login = updateUsuarioDTO.login();
        if(!EMAIL_PATTERN.matcher(login).matches()){
            throw new ValidacaoException("Não foi possível editar o usuário.<br>Motivo: o login informado não representa " +
                    "um email válido.",400);
        }
    }
}