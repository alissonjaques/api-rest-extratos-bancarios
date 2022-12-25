package br.com.banco.transferencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
    List<Transferencia> findByConta(Integer conta);
    List<Transferencia> findByOperador(String operador);

    @Query(value = "SELECT * FROM transferencia " +
            "WHERE data_transferencia BETWEEN :dataInicio AND :dataFim",
            nativeQuery = true)
    List<Transferencia> findByDateTime(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query(value = "SELECT * FROM transferencia " +
            "WHERE nome_operador_transacao = :operador AND data_transferencia BETWEEN :dataInicio AND :dataFim",
            nativeQuery = true)
    List<Transferencia> findByOperadorDateTime(String operador, LocalDateTime dataInicio, LocalDateTime dataFim);
}
