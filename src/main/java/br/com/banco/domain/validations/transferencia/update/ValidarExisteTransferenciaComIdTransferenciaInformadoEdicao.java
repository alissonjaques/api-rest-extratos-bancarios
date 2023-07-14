package br.com.banco.domain.validations.transferencia.update;

import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteTransferenciaComIdTransferenciaInformadoEdicao implements IValidacaoEditarTransferencia {
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public void validar(UpdateTransferenciaDTO updateTransferenciaDTO){
        Long idTransferencia = updateTransferenciaDTO.idTransferencia();
        var transferencia = transferenciaRepository.findByIdTransferencia(idTransferencia);

        if(transferencia == null){
            throw new ValidacaoException("Não foi possível editar a transferência no banco de dados.<br>Motivo: não existe " +
                    "transferência com idTransferencia = " + idTransferencia + " cadastrada no sistema.",400);
        }
    }
}
