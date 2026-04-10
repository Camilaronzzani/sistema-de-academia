package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import entity.StatusDisponibilidade;

@Entity
@Table(name = "disponibilidades")
public class DisponibilidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidade")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personal", nullable = false)
    private PersonalEntity personal;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusDisponibilidade status;

    public DisponibilidadeEntity() {}

    public DisponibilidadeEntity(PersonalEntity personal, LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        this.personal = personal;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.status = StatusDisponibilidade.ATIVO;
    }

    public Long getId() { return id; }

    public PersonalEntity getPersonal() { return personal; }
    public void setPersonal(PersonalEntity personal) { this.personal = personal; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFim() { return horaFim; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }

    public StatusDisponibilidade getStatus() { return status; }
    public void setStatus(StatusDisponibilidade status) { this.status = status; }
}
