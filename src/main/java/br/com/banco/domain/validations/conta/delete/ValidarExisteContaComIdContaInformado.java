package br.com.banco.domain.validations.conta.delete;

import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteContaComIdContaInformado implements IValidacaoDeletarConta{
    @Autowired
    private ContaRepository contaRepository;

    public void validar(Long idConta){
        var conta = contaRepository.findByIdConta(idConta);

        if(conta == null){
            throw new ValidacaoException("Não foi possível excluir a conta no banco de dados.<br>Motivo: não existe " +
                    "conta com idConta = " + idConta + " cadastrada no sistema.",400);
        }
    }
}
