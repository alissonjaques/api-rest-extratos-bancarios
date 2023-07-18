package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarExisteConta implements IValidacaoCriarTransferencia {
    @Autowired
    ContaRepository contaRepository;

    public void validar(CreateTransferenciaDTO createTransferenciaDTO){
        var conta = contaRepository.findByIdConta(createTransferenciaDTO.contaId());
        if(conta == null){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: não " +
                    "existe conta com idConta = " + createTransferenciaDTO.contaId() + " cadastrada no sistema.",400);
        }
    }
}
