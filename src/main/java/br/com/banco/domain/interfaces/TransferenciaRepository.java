package br.com.banco.domain.interfaces;

import br.com.banco.domain.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query(value = "SELECT SUM(valor) AS valor FROM transferencias " +
            "WHERE date(data_transferencia) BETWEEN :dataInicio AND :dataFim",
            nativeQuery = true)
    String somaDoValorPorPeriodo(Date dataInicio, Date dataFim);

    @Query("""
             select t from Transferencia t
             where t.idTransferencia = :idTransferencia
            """)
    Transferencia findByIdTransferencia(Long idTransferencia);

    @Query(value = "SELECT * FROM transferencias " +
            "WHERE date(data_transferencia) = :data",
            nativeQuery = true)
    List<Transferencia> findByDataTransferencia(Date data);

    @Query(value = "SELECT * FROM transferencias " +
            "WHERE date(data_transferencia) BETWEEN :dataInicio AND :dataFim",
            nativeQuery = true)
    List<Transferencia> findByDataFimAndDataInicio(Date dataInicio, Date dataFim);

    @Query(value = "SELECT * FROM transferencias " +
            "WHERE nome_operador_transacao = :nomeOperadorTransacao",
            nativeQuery = true)
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

    @Query(value = "SELECT * FROM transferencias " +
            "WHERE nome_operador_transacao = :nomeOperadorTransacao " +
            "AND date(data_transferencia) = :data",
            nativeQuery = true)
    List<Transferencia> findByNomeOperadorTransacaoAndData(String nomeOperadorTransacao, Date data);

    @Query(value = "SELECT * FROM transferencias " +
            "WHERE nome_operador_transacao = :nomeOperadorTransacao AND date(data_transferencia) BETWEEN :dataInicio " +
            "AND :dataFim",
            nativeQuery = true)
    List<Transferencia> findByAllArguments(String nomeOperadorTransacao, Date dataInicio, Date dataFim);
}
