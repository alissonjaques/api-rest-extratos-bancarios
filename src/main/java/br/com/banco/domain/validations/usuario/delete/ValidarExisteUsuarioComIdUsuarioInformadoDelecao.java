package br.com.banco.domain.validations.usuario.delete;

import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteUsuarioComIdUsuarioInformadoDelecao implements IValidacaoDeletarUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public void validar(Long idUsuario){
        var usuario = usuarioRepository.findByIdUsuario(idUsuario);

        if(usuario == null){
            throw new ValidacaoException("Não foi possível excluir o usuário no banco de dados.<br>Motivo: não existe " +
                    "usuario com idUsuario = " + idUsuario + " cadastrado no sistema.",400);
        }
    }
}
