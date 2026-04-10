package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkins")
public class CheckinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_checkin")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_agendamento", nullable = false)
    private AgendamentoEntity agendamento;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private AlunoEntity aluno;

    @Column(name = "data_hora_checkin", nullable = false)
    private LocalDateTime dataHoraCheckin;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo", length = 50)
    private MetodoCheckin metodo;

    @Column(name = "confirmado", nullable = false)
    private int confirmado = 1;

    @Column(name = "obs", columnDefinition = "TEXT")
    private String obs;

    public CheckinEntity() {}

    public CheckinEntity(AgendamentoEntity agendamento, AlunoEntity aluno) {
        this.agendamento = agendamento;
        this.aluno = aluno;
        this.dataHoraCheckin = LocalDateTime.now();
        this.confirmado = 1;
    }

    public Long getId() { return id; }

    public AgendamentoEntity getAgendamento() { return agendamento; }
    public void setAgendamento(AgendamentoEntity agendamento) { this.agendamento = agendamento; }

    public AlunoEntity getAluno() { return aluno; }
    public void setAluno(AlunoEntity aluno) { this.aluno = aluno; }

    public LocalDateTime getDataHoraCheckin() { return dataHoraCheckin; }
    public void setDataHoraCheckin(LocalDateTime dataHoraCheckin) { this.dataHoraCheckin = dataHoraCheckin; }

    public MetodoCheckin getMetodo() { return metodo; }
    public void setMetodo(MetodoCheckin metodo) { this.metodo = metodo; }

    public int getConfirmado() { return confirmado; }
    public void setConfirmado(int confirmado) { this.confirmado = confirmado; }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }
}
