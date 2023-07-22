package br.com.banco.domain.validations.usuario.autenticacao;

import br.com.banco.application.DTOs.usuario.AutenticacaoDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCamposObrigatoriosLogar implements IValidacaoLogar {
    public void validar(AutenticacaoDTO autenticacaoDTO){
        if(autenticacaoDTO.login() == null || autenticacaoDTO.login().isBlank() ){
            throw new ValidacaoException("Não foi possível logar no sistema.<br>Motivo: o campo login é obrigatório.",
                    400);
        }

        if(autenticacaoDTO.senha() == null || autenticacaoDTO.senha().isBlank()){
            throw new ValidacaoException("Não foi possível logar no sistema.<br>Motivo: o campo senha é obrigatório.",
                    400);
        }
    }
}