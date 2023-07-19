package br.com.banco.controller;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.GetTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.interfaces.TransferenciaRepository;
import br.com.banco.domain.service.TransferenciaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
    public ResponseEntity<Page<GetTransferenciaDTO>> listar(@RequestParam(required = false) Date dataInicio,
                                                            @RequestParam(required = false) Date dataFim,
                                                            @RequestParam(required = false) String nomeOperadorTransacao,
                                                            @PageableDefault(size = 10, page = 0, sort = {"idTransferencia"})
                                                            Pageable paginacao) {
        int start = paginacao.getPageNumber() * paginacao.getPageSize();

        // Apenas data de ínicio na requisição
        if (dataInicio != null && dataFim == null && nomeOperadorTransacao == null) {
            var stream = transferenciaRepository.findByDataTransferencia(dataInicio).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Apenas data de fim na requisição
        if (dataInicio == null && dataFim != null && nomeOperadorTransacao == null) {
            var stream = transferenciaRepository.findByDataTransferencia(dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Apenas nome do operador da transação na requisição
        if (dataInicio == null && dataFim == null && nomeOperadorTransacao != null) {
            var stream = transferenciaRepository
                    .findByNomeOperadorTransacao(nomeOperadorTransacao).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Data de início e data de fim na requisição, porém nome operador transação nulo
        if(dataInicio != null && dataFim != null && nomeOperadorTransacao == null){
            var stream = transferenciaRepository
                    .findByDataFimAndDataInicio(dataInicio,dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Data de início e nome operador transação na requisição, porém data de fim nula
        if(dataInicio != null && dataFim == null && nomeOperadorTransacao != null){
            var stream = transferenciaRepository
                    .findByNomeOperadorTransacaoAndData(nomeOperadorTransacao,dataInicio).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Data de fim e nome operador transação na requisição, porém data de início nula
        if(dataInicio == null && dataFim != null && nomeOperadorTransacao != null){
            var stream = transferenciaRepository
                    .findByNomeOperadorTransacaoAndData(nomeOperadorTransacao,dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Todos os argumentos na requisição
        if(dataInicio != null && dataFim != null && nomeOperadorTransacao != null){
            var stream = transferenciaRepository
                    .findByAllArguments(nomeOperadorTransacao,dataInicio,dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var tranferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), tranferencias.size());
            var page = new PageImpl<>(tranferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), tranferencias.size());
            return ResponseEntity.ok(page);
        }

        // Nenhum argumento na requisição
        var page = transferenciaRepository.findAll(paginacao).map(GetTransferenciaDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idTransferencia}")
    public ResponseEntity listarPorId(@PathVariable Long idTransferencia) {
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
    public ResponseEntity excluir(@PathVariable Long idTransferencia) {
        var transferencia = transferenciaRepository.getReferenceById(idTransferencia);
        transferenciaService.excluir(idTransferencia);
        return ResponseEntity.noContent().build();
    }
}
