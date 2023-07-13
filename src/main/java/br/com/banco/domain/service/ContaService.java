package br.com.banco.domain.service;

import br.com.banco.application.DTOs.conta.CreateContaDTO;
import br.com.banco.application.DTOs.conta.UpdateContaDTO;
import br.com.banco.domain.interfaces.ContaRepository;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.validations.conta.create.IValidacaoCriarConta;
import br.com.banco.domain.validations.conta.delete.IValidacaoDeletarConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    List<IValidacaoCriarConta> validacoesCriarConta;
    @Autowired
    List<IValidacaoDeletarConta> validacoesDeletarConta;

    public Conta cadastrar(CreateContaDTO createContaDTO){
        validacoesCriarConta.forEach(v -> v.validar(createContaDTO));
        var conta = new Conta(createContaDTO);
        contaRepository.save(conta);
        return conta;
    }

    public Conta atualizar(UpdateContaDTO updateContaDTO) {
        var conta = contaRepository.getReferenceById(updateContaDTO.idConta());
        conta.atualizarInformacoes(updateContaDTO);
        return conta;
    }

    public void excluir(Long idConta) {
        validacoesDeletarConta.forEach(v -> v.validar(idConta));
        var conta = contaRepository.getReferenceById(idConta);
        contaRepository.delete(conta);
    }
}
