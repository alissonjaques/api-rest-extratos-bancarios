package br.com.banco.domain.model;

import br.com.banco.domain.enums.Tipo;
import br.com.banco.domain.model.Conta;
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
    private Integer idTransferencia;

    @Column(name = "data_transferencia")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataTransferencia;

    private Double valor;

    private Tipo tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private Conta conta;
}
