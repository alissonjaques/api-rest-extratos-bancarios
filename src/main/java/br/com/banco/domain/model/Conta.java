package br.com.banco.domain.model;

import br.com.banco.application.DTOs.conta.CreateContaDTO;
import br.com.banco.application.DTOs.conta.UpdateContaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idConta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    public Conta(CreateContaDTO dados) {
        this.nomeResponsavel = dados.nomeResponsavel();
    }

    public void atualizarInformacoes(UpdateContaDTO dados) {
        if (dados.nomeResponsavel() != null) {
            this.nomeResponsavel = dados.nomeResponsavel();
        }
    }
}
