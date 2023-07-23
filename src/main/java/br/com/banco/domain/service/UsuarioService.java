package br.com.banco.domain.service;

import br.com.banco.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.interfaces.UsuarioRepository;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.validations.usuario.delete.IValidacaoDeletarUsuario;
import br.com.banco.domain.validations.usuario.post.IValidacaoCriarUsuario;
import br.com.banco.domain.validations.usuario.update.IValidacaoEditarUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<IValidacaoCriarUsuario> validacoesCriarUsuario;
    @Autowired
    List<IValidacaoDeletarUsuario> validacoesDeletarUsuario;
    @Autowired
    List<IValidacaoEditarUsuario> validacoesEditarUsuario;
    public Usuario cadastrar(CreateUsuarioDTO createUsuarioDTO){
        validacoesCriarUsuario.forEach(v -> v.validar(createUsuarioDTO));
        var usuario = new Usuario(createUsuarioDTO);
        usuarioRepository.save(usuario);
        return usuario;
    }
    public Usuario atualizar(UpdateUsuarioDTO updateUsuarioDTO) {
        validacoesEditarUsuario.forEach(v -> v.validar(updateUsuarioDTO));
        var usuario = usuarioRepository.getReferenceById(updateUsuarioDTO.idUsuario());
        usuario.atualizarInformacoes(updateUsuarioDTO);
        return usuario;
    }
    public void excluir(Long idUsuario) {
        validacoesDeletarUsuario.forEach(v -> v.validar(idUsuario));
        var usuario = usuarioRepository.getReferenceById(idUsuario);
        usuarioRepository.delete(usuario);
    }
}
