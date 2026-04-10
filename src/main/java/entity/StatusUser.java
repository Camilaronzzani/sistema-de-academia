package entity;

public enum StatusUser {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    StatusUser(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
