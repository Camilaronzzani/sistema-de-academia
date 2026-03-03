package Camilaronzzani.com.github;

import java.time.LocalDate;
import java.time.LocalTime;

public class Aula {
    private Aluno aluno;
    private LocalDate data;
    private LocalTime horario;
    private  boolean compareceu;


    public Aula(Aluno aluno, LocalDate data, LocalTime horario) {
        this.aluno = aluno;
        this.data = data;
        this.horario = horario;
        this.compareceu = false; // Inicia como não compareceu

    }

    public boolean isCompareceu() {
        return compareceu;
    }

    public void setCompareceu(boolean compareceu) {
        this.compareceu = compareceu;
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
