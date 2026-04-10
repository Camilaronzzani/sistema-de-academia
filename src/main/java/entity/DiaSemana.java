package entity;

public enum DiaSemana {
    SEGUNDA("Segunda"),
    TERCA("Terca"),
    QUARTA("Quarta"),
    QUINTA("Quinta"),
    SEXTA("Sexta"),
    SABADO("Sabado"),
    DOMINGO("Domingo");

    private final String descricao;

    DiaSemana(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
