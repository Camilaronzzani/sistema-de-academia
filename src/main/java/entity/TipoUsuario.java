package entity;

public enum TipoUsuario {
    ALUNO("Aluno"),
    PERSONAL("Personal");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
