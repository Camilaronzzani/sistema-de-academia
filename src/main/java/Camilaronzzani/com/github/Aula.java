package Camilaronzzani.com.github;

import java.time.LocalDate;
import java.time.LocalTime;
import entity.StatusPresenca;

public class Aula {
    private Aluno aluno;
    private LocalDate data;
    private LocalTime horario;
    private StatusPresenca statusPresenca;


    public Aula(Aluno aluno, LocalDate data, LocalTime horario) {
        this.aluno = aluno;
        this.data = data;
        this.horario = horario;
        this.statusPresenca = StatusPresenca.AGENDADO;
    }

    public StatusPresenca getStatusPresenca() {
        return statusPresenca;
    }

    public void setStatusPresenca(StatusPresenca statusPresenca) {
        this.statusPresenca = statusPresenca;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
