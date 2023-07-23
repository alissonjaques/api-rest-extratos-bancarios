package br.com.banco.controller;

import br.com.banco.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.banco.application.DTOs.usuario.GetUsuarioDTO;
import br.com.banco.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.banco.domain.interfaces.UsuarioRepository;
import br.com.banco.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreateUsuarioDTO createUsuarioDTO, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrar(createUsuarioDTO);
        var uri = uriBuilder.path("/usuarios/{idUsuario}").buildAndExpand(usuario.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(new GetUsuarioDTO(usuario));
    }
    @GetMapping
    public ResponseEntity<Page<GetUsuarioDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"idUsuario"})
                                                            Pageable paginacao){
        var page = usuarioRepository.findAll(paginacao).map(GetUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity listarPorId(@PathVariable Long idUsuario){
        var usuario = usuarioRepository.getReferenceById(idUsuario);
        return ResponseEntity.ok(new GetUsuarioDTO(usuario));
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdateUsuarioDTO updateUsuarioDTO) {
        var usuario = usuarioService.atualizar(updateUsuarioDTO);
        return ResponseEntity.ok(new GetUsuarioDTO(usuario));
    }
    @DeleteMapping("/{idUsuario}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long idUsuario){
        usuarioService.excluir(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
