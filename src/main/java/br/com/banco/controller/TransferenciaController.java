package br.com.banco.controller;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.GetRelatorioTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.GetTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.interfaces.TransferenciaRepository;
import br.com.banco.domain.service.TransferenciaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("transferencias")
@SecurityRequirement(name = "bearer-key")
public class TransferenciaController {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    TransferenciaService transferenciaService;
    @Autowired
    private ResourceLoader resourceLoader;

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
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
            return ResponseEntity.ok(page);
        }

        // Apenas data de fim na requisição
        if (dataInicio == null && dataFim != null && nomeOperadorTransacao == null) {
            var stream = transferenciaRepository.findByDataTransferencia(dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
            return ResponseEntity.ok(page);
        }

        // Apenas nome do operador da transação na requisição
        if (dataInicio == null && dataFim == null && nomeOperadorTransacao != null) {
            var stream = transferenciaRepository
                    .findByNomeOperadorTransacao(nomeOperadorTransacao).stream()
                    .map(GetTransferenciaDTO::new);
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
            return ResponseEntity.ok(page);
        }

        // Data de início e data de fim na requisição, porém nome operador transação nulo
        if(dataInicio != null && dataFim != null && nomeOperadorTransacao == null){
            var stream = transferenciaRepository
                    .findByDataFimAndDataInicio(dataInicio,dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
            return ResponseEntity.ok(page);
        }

        // Data de início e nome operador transação na requisição, porém data de fim nula
        if(dataInicio != null && dataFim == null && nomeOperadorTransacao != null){
            var stream = transferenciaRepository
                    .findByNomeOperadorTransacaoAndData(nomeOperadorTransacao,dataInicio).stream()
                    .map(GetTransferenciaDTO::new);
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
            return ResponseEntity.ok(page);
        }

        // Data de fim e nome operador transação na requisição, porém data de início nula
        if(dataInicio == null && dataFim != null && nomeOperadorTransacao != null){
            var stream = transferenciaRepository
                    .findByNomeOperadorTransacaoAndData(nomeOperadorTransacao,dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
            return ResponseEntity.ok(page);
        }

        // Todos os argumentos na requisição
        if(dataInicio != null && dataFim != null && nomeOperadorTransacao != null){
            var stream = transferenciaRepository
                    .findByAllArguments(nomeOperadorTransacao,dataInicio,dataFim).stream()
                    .map(GetTransferenciaDTO::new);
            var transferencias = stream.collect(Collectors.toList());
            int end = Math.min((start + paginacao.getPageSize()), transferencias.size());
            var page = new PageImpl<>(transferencias.subList(start, end),
                    PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), transferencias.size());
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

    @GetMapping("/relatorioPorPeriodo")
    public ResponseEntity gerarRelatorioPorPeriodo(@RequestParam(required = false) Date dataInicio,
                                                   @RequestParam(required = false) Date dataFim)
            throws IOException{
        String caminhoRelatorio = transferenciaService.gerarRelatorioPorPeriodo(new GetRelatorioTransferenciaDTO(dataInicio,dataFim));
        Resource resource = resourceLoader.getResource("file:" + caminhoRelatorio);
        byte[] pdfContent = Files.readAllBytes(resource.getFile().toPath());

        // Configurar os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio.pdf"); // Pode ser usado para forçar o download

        // Retornar a resposta com o conteúdo do PDF no corpo
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
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
