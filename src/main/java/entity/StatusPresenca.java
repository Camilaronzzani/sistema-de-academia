package entity;

public enum StatusPresenca {
    AGENDADO("Agendado"),
    PRESENTE("Presente"),
    FALTOU("Faltou");

    private final String descricao;

    StatusPresenca(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
