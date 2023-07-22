package br.com.banco.domain.validations.usuario.autenticacao;

import br.com.banco.application.DTOs.usuario.AutenticacaoDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component()
public class ValidarCredenciais implements IValidacaoLogar {
    @Autowired
    UsuarioRepository usuarioRepository;
    public void validar(AutenticacaoDTO autenticacaoDTO){
        var usuario = usuarioRepository.findByLogin(autenticacaoDTO.login());

        if(usuario == null){
            throw new ValidacaoException("Não foi possível logar no sistema.<br>Motivo: login ou senha inválidos.",
                    403);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean senhasIguais = encoder.matches(autenticacaoDTO.senha(), usuario.getPassword());

        if(!senhasIguais){
            throw new ValidacaoException("Não foi possível logar no sistema.<br>Motivo: login ou senha inválidos.",
                    403);
        }
    }
}