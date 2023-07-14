package br.com.banco.domain.validations.conta.update;

import br.com.banco.application.DTOs.conta.UpdateContaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import br.com.banco.domain.interfaces.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class ValidarExisteContaComIdContaInformadoEdicao implements IValidacaoEditarConta {
    @Autowired
    private ContaRepository contaRepository;

    public void validar(UpdateContaDTO updateContaDTO){
        Long idConta = updateContaDTO.idConta();
        var conta = contaRepository.findByIdConta(idConta);

        if(conta == null){
            throw new ValidacaoException("Não foi possível editar a conta no banco de dados.<br>Motivo: não existe " +
                    "conta com idConta = " + idConta + " cadastrada no sistema.",400);
        }
    }
}
