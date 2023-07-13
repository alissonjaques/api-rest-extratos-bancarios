package br.com.banco.domain.enums;

public enum Tipo {
    DEPOSITO("Depósito"),
    ENTRADA("Transferência Entrada"),
    SAIDA("Transferência Saída"),
    SAQUE("Saque");

    private final String descricao;

    private Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
