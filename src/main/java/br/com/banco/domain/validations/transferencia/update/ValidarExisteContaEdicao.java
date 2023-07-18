package br.com.banco.domain.validations.transferencia.update;

import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteContaEdicao implements IValidacaoEditarTransferencia {
    @Autowired
    ContaRepository contaRepository;

    public void validar(UpdateTransferenciaDTO updateTransferenciaDTO){
        var conta = contaRepository.findByIdConta(updateTransferenciaDTO.contaId());
        if(conta == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: não " +
                    "existe conta com idConta = " + updateTransferenciaDTO.contaId() + " cadastrada no sistema.",400);
        }
    }
}
