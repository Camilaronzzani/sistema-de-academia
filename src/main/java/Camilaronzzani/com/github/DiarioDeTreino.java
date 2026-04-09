package Camilaronzzani.com.github;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import entity.StatusPresenca;

public class DiarioDeTreino {

    private List<Aula> registros = new ArrayList<>();

    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

    public void anotarNovoTreino(Aula aula) {
        registros.add(aula);
        System.out.println("Aula agendada para " + aula.getAluno().getNome() + " no dia " + aula.getData().format(formatadorData));
    }

    public void exibirTodosOsTreinos() {
        if (registros.isEmpty()) {
            System.out.println("Nao tem nenhum treino cadastrado ainda.");
            return;
        }
        for (Aula a : registros) {
            System.out.println(a.getAluno().getNome() + " - " + a.getData().format(formatadorData) + " " + a.getHorario().format(formatadorHora) + " - " + a.getStatusPresenca().getDescricao());
        }
    }

    public void marcarPresencaNoDiario(String nomeAluno, LocalDate data) {
        boolean encontrou = false;
        for (Aula a : registros) {
            if (a.getAluno().getNome().equalsIgnoreCase(nomeAluno) && a.getData().equals(data)) {
                if (a.getStatusPresenca() == StatusPresenca.PRESENTE) {
                    System.out.println("Presenca ja foi marcada antes.");
                } else {
                    a.setStatusPresenca(StatusPresenca.PRESENTE);
                    System.out.println("Presenca marcada com sucesso!");
                }
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nao encontrei nenhuma aula para esse aluno nessa data.");
        }
    }

    public void verFaltasDoDia(LocalDate data) {
        System.out.println("Alunos que nao vieram em " + data.format(formatadorData) + ":");
        boolean temAlguem = false;
        for (Aula a : registros) {
            if (a.getData().equals(data) && a.getStatusPresenca() != StatusPresenca.PRESENTE) {
                System.out.println("- " + a.getAluno().getNome() + " as " + a.getHorario().format(formatadorHora));
                temAlguem = true;
            }
        }
        if (!temAlguem) {
            System.out.println("Todo mundo veio!");
        }
    }

    public boolean conflitoDeHorario(String cpf, LocalDate data, LocalTime horario) {
        for (Aula a : registros) {
            if (a.getAluno().getCpf().equals(cpf) && a.getData().equals(data) && a.getHorario().equals(horario)) {
                return true;
            }
        }
        return false;
    }

    public List<Aula> getRegistros() {
        return registros;
    }
}
