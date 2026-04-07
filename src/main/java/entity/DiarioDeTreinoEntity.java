package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "diarios_treino")
    public class DiarioDeTreinoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_diario")
        private Long id;

        // Relacionamento: Muitos diários para um aluno
        @ManyToOne
        @JoinColumn(name = "id_aluno", nullable = false)
        private AlunoEntity aluno;

        @Column(name = "data_hora_treino", nullable = false)
        private LocalDateTime dataHora;

        @Column(name = "descricao_exercicios", columnDefinition = "TEXT")
        private String descricao;

        @Column(name = "observacoes", length = 255)
        private String observacoes;

        @Column(name = "presenca", nullable = false)
        private boolean presenca;

        // Construtor Padrão
        public DiarioDeTreinoEntity() {}

        // Construtor com campos principais
        public DiarioDeTreinoEntity(AlunoEntity aluno, LocalDateTime dataHora, String descricao, boolean presenca) {
            this.aluno = aluno;
            this.dataHora = dataHora;
            this.descricao = descricao;
            this.presenca = presenca;
        }

        // Getters e Setters
        public Long getId() { return id; }

        public AlunoEntity getAluno() { return aluno; }
        public void setAluno(AlunoEntity aluno) { this.aluno = aluno; }

        public LocalDateTime getDataHora() { return dataHora; }
        public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }

        public String getObservacoes() { return observacoes; }
        public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

        public boolean isPresenca() { return presenca; }
        public void setPresenca(boolean presenca) { this.presenca = presenca; }
    }
