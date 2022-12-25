package br.com.banco.controller;

import br.com.banco.transferencia.Transferencia;
import br.com.banco.transferencia.TransferenciaRepository;
import br.com.banco.utils.BibliotecaDeMetodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController implements ITransferenciaController {
    @Autowired
    private TransferenciaRepository repository;
    /**
     * Busca todas as transferencias realizadas pela API que estejam listadas na base de dados
     * @return lista com todas as transferencias
     * */
    @CrossOrigin(origins = "http://localhost:3000") // CrossOrigin da aplicação
    @GetMapping
    @Override
    public List<Transferencia> listar() {
        return repository.findAll();
    }

    /**
     * Busca todas as transferencias realizados por uma conta.
     * @param conta o numero da conta
     * @return lista de transações correspondentes à conta informada
     * */
    @CrossOrigin(origins = "http://localhost:3000") // CrossOrigin da aplicação
    @GetMapping(value = "/search==cc{conta}")
    @Override
    public List<Transferencia> buscar(@PathVariable Integer conta) {
        return repository.findByConta(conta);
    }

    /**
     * Busca todas as transferencias realizados por um operador
     * @param operador o operador
     * @return lista de transações a partir do nome do operador
     * */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/search==oc{operador}")
    @Override
    public List<Transferencia> buscar(@PathVariable String operador) {
        if (operador.equals("null")) operador = null;

        return repository.findByOperador(operador);
    }

    /**
     * Busca todas as transferencias realizados dentro de um periodo de tempo.
     * @param dataInicio a data inicial
     * @param dataFim a data final
     * @return lista de transações que foram realizadas entre de dataInicio e dataFim
     * */
    @CrossOrigin(origins = "http://localhost:3000") // CrossOrigin da aplicação
    @GetMapping(value = "/search==dataInicio{dataInicio}&dataFim{dataFim}")
    @Override
    public List<Transferencia> buscar(@PathVariable(value = "dataInicio") String dataInicio,
                                      @PathVariable(value = "dataFim") String dataFim) {
        return repository.findByDateTime(BibliotecaDeMetodos.stringParaLocalDateTime(dataInicio),
                BibliotecaDeMetodos.stringParaLocalDateTime(dataFim));
    }

    /**
     * Busca todas as transferencias que um usuario realizou dentro de um periodo de tempo.
     * @param operador nome
     * @param dataInicio a data inicial
     * @param dataFim a data final
     * @return lista de transações que foram realizadas entre de dataInicio e dataFim
     * */
    @CrossOrigin(origins = "http://localhost:3000") // CrossOrigin da aplicação
    @GetMapping(value = "/search=={operador}&dataInicio{dataInicio}&dataFim{dataFim}")
    @Override
    public List<Transferencia> buscar(@PathVariable(value = "operador") String operador,
                                      @PathVariable(value = "dataInicio") String dataInicio,
                                      @PathVariable(value = "dataFim") String dataFim) {
        return repository.findByOperadorDateTime(operador,BibliotecaDeMetodos.stringParaLocalDateTime(dataInicio),
                BibliotecaDeMetodos.stringParaLocalDateTime(dataFim));
    }
}