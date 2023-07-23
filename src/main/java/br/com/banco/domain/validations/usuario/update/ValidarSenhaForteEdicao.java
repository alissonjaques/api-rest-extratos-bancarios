package br.com.banco.domain.validations.usuario.update;

import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component()
public class ValidarSenhaForteEdicao implements IValidacaoEditarUsuario {
    public void validar(UpdateUsuarioDTO updateUsuarioDTO){
        String senha = updateUsuarioDTO.senha();
        String mensagem = "Não foi possível editar o usuário.<br>Motivo: a senha informada é fraca. A senha deve conter:" +
                            "<br> - Pelo menos 8 caracters;" +
                            "<br> - Letras maiúsculas e minúsculas;" +
                            "<br> - Números;" +
                            "<br> - Caracteres especiais.";

        if (senha == null || senha.isEmpty()) {
            throw new ValidacaoException(mensagem,400);
        }

        // Verificar se a senha tem pelo menos 8 caracteres
        if (senha.length() < 8) {
            throw new ValidacaoException(mensagem,400);
        }

        // Verificar se a senha contém letras maiúsculas e minúsculas
        boolean hasUppercase = !senha.equals(senha.toLowerCase());
        boolean hasLowercase = !senha.equals(senha.toUpperCase());
        if (!hasUppercase || !hasLowercase) {
            throw new ValidacaoException(mensagem,400);
        }

        // Verificar se a senha contém números
        if (!senha.matches(".*\\d.*")) {
            throw new ValidacaoException(mensagem,400);
        }

        // Verificar se a senha contém caracteres especiais
        Pattern specialCharsPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = specialCharsPattern.matcher(senha);
        if (!matcher.find()) {
            throw new ValidacaoException(mensagem,400);
        }
    }
}