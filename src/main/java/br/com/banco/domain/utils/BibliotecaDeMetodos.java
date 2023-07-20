package br.com.banco.domain.utils;

import br.com.banco.application.DTOs.transferencia.GetTransferenciaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BibliotecaDeMetodos {
    /**
     * Este método calcula o saldo total das transferências passadas como argumento.
     * @param transferencias a lista de transferencias
     * @return o saldo total (soma dos valores das transferencias)
     * */
    public static double getSaldoPeriodo(List<GetTransferenciaDTO> transferencias){
        double saldo = 0;
        for (GetTransferenciaDTO transferencia : transferencias) {
            saldo += transferencia.valor();
        }

        return saldo;
    }
}
