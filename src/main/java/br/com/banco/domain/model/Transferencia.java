package br.com.banco.domain.model;

import br.com.banco.application.DTOs.transferencia.CreateTransferenciaDTO;
import br.com.banco.application.DTOs.transferencia.UpdateTransferenciaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "transferencias")
@Entity(name = "Transferencia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idTransferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Long idTransferencia;

    @Column(name = "data_transferencia")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataTransferencia;

    private Double valor;

    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Transferencia(CreateTransferenciaDTO createTransferenciaDTO) {
        this.dataTransferencia = createTransferenciaDTO.dataTransferencia();
        this.valor = createTransferenciaDTO.valor();
        this.tipo = createTransferenciaDTO.tipo().getDescricao();
        this.nomeOperadorTransacao = createTransferenciaDTO.nomeOperadorTransacao();
        this.conta = new Conta(createTransferenciaDTO.contaId());
    }

    public void atualizarInformacoes(UpdateTransferenciaDTO updateTransferenciaDTO) {
        if (updateTransferenciaDTO.dataTransferencia() != null) {
            this.dataTransferencia = updateTransferenciaDTO.dataTransferencia();
        }

        if (updateTransferenciaDTO.valor() != null) {
            this.valor = updateTransferenciaDTO.valor();
        }

        if (updateTransferenciaDTO.tipo() != null) {
            this.tipo = updateTransferenciaDTO.tipo().getDescricao();
        }

        if (updateTransferenciaDTO.nomeOperadorTransacao() != null) {
            this.nomeOperadorTransacao = updateTransferenciaDTO.nomeOperadorTransacao();
        }

        if (updateTransferenciaDTO.contaId() != null) {
            this.conta = new Conta(updateTransferenciaDTO.contaId());
        }
    }
}
