package br.com.banco.domain.validations.transferencia.create;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component()
public class ValidarCasasDecimaisValor implements IValidacaoCriarTransferencia {
    public void validar(CreateTransferenciaDTO createTransferenciaDTO){
        System.out.println("Aqui");
        if(!possuiDuasCasasDecimais(createTransferenciaDTO.valor())){
            throw new ValidacaoException("Não foi possível cadastrar a transferência no banco de dados.<br>Motivo: " +
                    "o valor da transferência deve possuir, no máximo, duas casas decimais.",400);
        }
    }

    public boolean possuiDuasCasasDecimais(double valor) {
        String valorString = String.valueOf(valor);
        System.out.println(valorString);
        String[] partes = valorString.split("\\.");

        if (partes.length == 2) {
            String casasDecimais = partes[1];
            System.out.println(casasDecimais);
            return casasDecimais.length() <= 2;
        }
        return true;
    }
}
