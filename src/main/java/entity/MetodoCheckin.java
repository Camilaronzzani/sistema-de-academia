package entity;

public enum MetodoCheckin {
    SISTEMA("Sistema"),
    FACE_ID("Face ID");

    private final String descricao;

    MetodoCheckin(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
