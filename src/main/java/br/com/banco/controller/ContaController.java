package br.com.banco.controller;

import br.com.banco.application.DTOs.conta.CreateContaDTO;
import br.com.banco.application.DTOs.conta.GetContaDTO;
import br.com.banco.application.DTOs.conta.UpdateContaDTO;
import br.com.banco.domain.interfaces.ContaRepository;
import br.com.banco.domain.service.ContaService;
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
@RequestMapping("contas")
@SecurityRequirement(name = "bearer-key")
public class ContaController {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    ContaService contaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreateContaDTO createContaDTO, UriComponentsBuilder uriBuilder) {
        var conta = contaService.cadastrar(createContaDTO);
        var uri = uriBuilder.path("/contas/{idConta}").buildAndExpand(conta.getIdConta()).toUri();
        return ResponseEntity.created(uri).body(new GetContaDTO(conta));
    }

    @GetMapping
    public ResponseEntity<Page<GetContaDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"idConta"})
                                                            Pageable paginacao){
        var page = contaRepository.findAll(paginacao).map(GetContaDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idConta}")
    public ResponseEntity listarPorId(@PathVariable Long idConta){
        var conta = contaRepository.getReferenceById(idConta);
        return ResponseEntity.ok(new GetContaDTO(conta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdateContaDTO updateContaDTO) {
        var conta = contaService.atualizar(updateContaDTO);
        return ResponseEntity.ok(new GetContaDTO(conta));
    }

    @DeleteMapping("/{idConta}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long idConta){
        contaService.excluir(idConta);
        return ResponseEntity.noContent().build();
    }
}
