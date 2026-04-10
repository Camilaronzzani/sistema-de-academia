package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "id_disponibilidade")
    private DisponibilidadeEntity disponibilidade;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private StatusAgendamento status;

    @Column(name = "obs", columnDefinition = "TEXT")
    private String obs;

    @OneToOne(mappedBy = "agendamento")
    private CheckinEntity checkin;

    public AgendamentoEntity() {}

    public AgendamentoEntity(AlunoEntity aluno, DisponibilidadeEntity disponibilidade, LocalDateTime dataHora) {
        this.aluno = aluno;
        this.disponibilidade = disponibilidade;
        this.dataHora = dataHora;
        this.status = StatusAgendamento.AGENDADO;
    }

    public Long getId() { return id; }

    public AlunoEntity getAluno() { return aluno; }
    public void setAluno(AlunoEntity aluno) { this.aluno = aluno; }

    public DisponibilidadeEntity getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(DisponibilidadeEntity disponibilidade) { this.disponibilidade = disponibilidade; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public StatusAgendamento getStatus() { return status; }
    public void setStatus(StatusAgendamento status) { this.status = status; }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }

    public CheckinEntity getCheckin() { return checkin; }
    public void setCheckin(CheckinEntity checkin) { this.checkin = checkin; }
}
