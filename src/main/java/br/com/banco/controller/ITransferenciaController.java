package br.com.banco.controller;

import br.com.banco.transferencia.Transferencia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;
import java.util.List;

public interface ITransferenciaController {
    List<Transferencia> listar();
    List<Transferencia> buscar(Integer conta);
    List<Transferencia> buscar(String operador);
    List<Transferencia> buscar(String inicio, String fim);
    List<Transferencia> buscar(String operador,String inicio,String fim);
}
