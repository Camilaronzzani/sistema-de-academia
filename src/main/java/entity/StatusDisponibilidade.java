package entity;

public enum StatusDisponibilidade {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    StatusDisponibilidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
