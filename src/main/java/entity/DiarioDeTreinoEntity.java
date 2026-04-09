package entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import entity.StatusPresenca;

@Entity
@Table(name = "diarios_treino")
public class DiarioDeTreinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diario")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private AlunoEntity aluno;

    @Column(name = "data_hora_treino", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "descricao_exercicios", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "observacoes", length = 255)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_presenca", nullable = false)
    private StatusPresenca statusPresenca;

    public DiarioDeTreinoEntity() {}

    public DiarioDeTreinoEntity(AlunoEntity aluno, LocalDateTime dataHora, String descricao, StatusPresenca statusPresenca) {
        this.aluno = aluno;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.statusPresenca = statusPresenca;
    }

    public Long getId() { return id; }

    public AlunoEntity getAluno() { return aluno; }
    public void setAluno(AlunoEntity aluno) { this.aluno = aluno; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public StatusPresenca getStatusPresenca() { return statusPresenca; }
    public void setStatusPresenca(StatusPresenca statusPresenca) { this.statusPresenca = statusPresenca; }
}
