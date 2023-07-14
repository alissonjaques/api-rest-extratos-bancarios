package br.com.banco.controller;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.GetTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.interfaces.TransferenciaRepository;
import br.com.banco.domain.service.TransferenciaService;
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
@RequestMapping("transferencias")
@SecurityRequirement(name = "bearer-key")
public class TransferenciaController {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    TransferenciaService transferenciaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreateTransferenciaDTO createTransferenciaDTO,
                                    UriComponentsBuilder uriBuilder) {
        var transferencia = transferenciaService.cadastrar(createTransferenciaDTO);
        var uri = uriBuilder.path("/transferencias/{idTransferencia}")
                .buildAndExpand(transferencia.getIdTransferencia()).toUri();
        return ResponseEntity.created(uri).body(new GetTransferenciaDTO(transferencia));
    }

    @GetMapping
    public ResponseEntity<Page<GetTransferenciaDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"idTransferencia"})
                                                    Pageable paginacao){
        var page = transferenciaRepository.findAll(paginacao).map(GetTransferenciaDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idTransferencia}")
    public ResponseEntity listarPorId(@PathVariable Long idTransferencia){
        var transferencia = transferenciaRepository.getReferenceById(idTransferencia);
        return ResponseEntity.ok(new GetTransferenciaDTO(transferencia));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdateTransferenciaDTO updateTransferenciaDTO) {
        var transferencia = transferenciaService.atualizar(updateTransferenciaDTO);
        return ResponseEntity.ok(new GetTransferenciaDTO(transferencia));
    }

    @DeleteMapping("/{idTransferencia}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long idTransferencia){
        var transferencia = transferenciaRepository.getReferenceById(idTransferencia);
        transferenciaService.excluir(idTransferencia);
        return ResponseEntity.noContent().build();
    }
}
