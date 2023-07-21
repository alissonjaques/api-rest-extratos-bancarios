package br.com.banco.domain.service;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.GetRelatorioTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.TransferenciaRepository;
import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.validations.transferencia.create.IValidacaoCriarTransferencia;
import br.com.banco.domain.validations.transferencia.delete.IValidacaoDeletarTransferencia;
import br.com.banco.domain.validations.transferencia.relatorios.IValidacaoRelatorioTransferencia;
import br.com.banco.domain.validations.transferencia.update.IValidacaoEditarTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    RelatorioService relatorioService;
    @Autowired
    List<IValidacaoCriarTransferencia> validacoesCriarTransferencia;
    @Autowired
    List<IValidacaoDeletarTransferencia> validacoesDeletarTransferencia;
    @Autowired
    List<IValidacaoEditarTransferencia> validacoesEditarTransferencia;

    @Autowired
    List<IValidacaoRelatorioTransferencia> validacoesRelatorioTransferencias;

    public Transferencia cadastrar(CreateTransferenciaDTO createTransferenciaDTO){
        validacoesCriarTransferencia.forEach(v -> v.validar(createTransferenciaDTO));
        var transferencia = new Transferencia(createTransferenciaDTO);
        transferenciaRepository.save(transferencia);
        return transferencia;
    }

    public Transferencia atualizar(UpdateTransferenciaDTO updateTransferenciaDTO) {
        validacoesEditarTransferencia.forEach(v -> v.validar(updateTransferenciaDTO));
        var transferencia = transferenciaRepository.getReferenceById(updateTransferenciaDTO.idTransferencia());
        transferencia.atualizarInformacoes(updateTransferenciaDTO);
        return transferencia;
    }

    public void excluir(Long idTransferencia) {
        validacoesDeletarTransferencia.forEach(v -> v.validar(idTransferencia));
        var transferencia = transferenciaRepository.getReferenceById(idTransferencia);
        transferenciaRepository.delete(transferencia);
    }

    public String gerarRelatorioPorPeriodo(GetRelatorioTransferenciaDTO getRelatorioTransferenciaDTO) {
        validacoesRelatorioTransferencias.forEach(v -> v.validar(getRelatorioTransferenciaDTO));
        try{
            List<String> parametros = new ArrayList();
            parametros.add("saldoPeriodo");
            parametros.add("dataInicio");
            parametros.add("dataFim");

            List<String> valores = new ArrayList();
            String valorTotal = String.valueOf(transferenciaRepository.somaDoValorPorPeriodo(getRelatorioTransferenciaDTO
                    .dataInicio(),getRelatorioTransferenciaDTO.dataFim()));
            valores.add(valorTotal);
            valores.add(getRelatorioTransferenciaDTO.dataInicio().toString());
            valores.add(getRelatorioTransferenciaDTO.dataFim().toString());
            return relatorioService.gerarRelatorio("relatorio_extrato_bancario", parametros, valores);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ValidacaoException("Não foi possível gerar o relatório.<br>Motivo: " + ex.getMessage(),500);
        }
    }
}
