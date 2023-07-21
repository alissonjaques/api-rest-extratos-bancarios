package br.com.banco.domain.validations.transferencia.relatorios;

import br.com.banco.application.DTOs.transferencia.GetRelatorioTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCamposRelatorioTransferencia implements IValidacaoRelatorioTransferencia {
    public void validar(GetRelatorioTransferenciaDTO getRelatorioTransferenciaDTO){
        if(getRelatorioTransferenciaDTO.dataInicio() == null){
            throw new ValidacaoException("Não foi possível gerar o relatório.<br>Motivo: o campo dataInicio é " +
                    "obrigatório.",400);
        }

        if(getRelatorioTransferenciaDTO.dataFim() == null){
            throw new ValidacaoException("Não foi possível gerar o relatório.<br>Motivo: o campo dataFim é " +
                    "obrigatório.",400);
        }
    }
}