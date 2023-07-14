package br.com.banco.domain.validations.transferencia.delete;

import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteTransferenciaComIdTransferenciaInformadoDelecao implements IValidacaoDeletarTransferencia {
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public void validar(Long idTransferencia){
        var transferencia = transferenciaRepository.findByIdTransferencia(idTransferencia);

        if(transferencia == null){
            throw new ValidacaoException("Não foi possível excluir a transferência no banco de dados.<br>Motivo: não " +
                    "existe transferência com idTransferencia = " + idTransferencia + " cadastrada no sistema.",400);
        }
    }
}
