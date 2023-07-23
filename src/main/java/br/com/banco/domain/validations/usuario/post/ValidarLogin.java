package br.com.banco.domain.validations.usuario.post;

import br.com.banco.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component()
public class ValidarLogin implements IValidacaoCriarUsuario {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public void validar(CreateUsuarioDTO createUsuarioDTO){
        String login = createUsuarioDTO.login();
        if(!EMAIL_PATTERN.matcher(login).matches()){
            throw new ValidacaoException("Não foi possível criar o usuário.<br>Motivo: o login informado não representa " +
                    "um email válido.",400);
        }
    }
}