package Camilaronzzani.com.github;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DiarioDeTreino {

    // A lista de registros
    private List<Aula> registros = new ArrayList<>();

    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");


    // Anotar treino, agora com a confirmação visual do usuário
    public void anotarNovoTreino(Aula aula) {
        try {
            registros.add(aula);
            System.out.println("\n--- AGENDAMENTO REALIZADO ---");
            System.out.println("Aluno: " + aula.getAluno().getNome() + " | Data: " + formatadorData.format(aula.getData()));
        } catch (Exception e) {
            System.out.println("Erro ao anotar treino: " + e.getMessage());
        }
    }

    //  Exibir todos os registros (O seu "DIÁRIO DE TREINOS")
    public void exibirTodosOsTreinos() {
        try {
            if (registros.isEmpty()) {
                System.out.println("\nO diário está vazio.");
                return;
            }
            System.out.println("\n--- DIÁRIO DE TREINOS COMPLETO ---");
            for (Aula a : registros) {
                String check = a.isCompareceu() ? "[V] TREINOU" : "[] AGENDADO";
                System.out.println(check + " | " + a.getAluno().getNome() + " | " + a.getData() + " às " + a.getHorario());
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir treinos: " + e.getMessage());
        }
    }

    public void marcarPresencaNoDiario(String nomeAluno, LocalDate data) {
        try {
            boolean encontrado = false;
            for (Aula a : registros) {
                if (a.getAluno().getNome().equalsIgnoreCase(nomeAluno) && a.getData().equals(data)) {
                    if (a.isCompareceu()) {
                        System.out.println("Aviso: Presença já registrada para " + a.getAluno().getNome() + " em " + data.format(formatadorData) + ".");
                    } else {
                        a.setCompareceu(true);
                        System.out.println("✓ Presença marcada: " + a.getAluno().getNome() + " em " + data.format(formatadorData) + " às " + a.getHorario().format(formatadorHora));
                    }
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Aviso: Nenhuma aula encontrada para '" + nomeAluno + "' em " + data.format(formatadorData) + ".");
            }
        } catch (Exception e) {
            System.out.println("Erro ao marcar presença: " + e.getMessage());
        }
    }

    // Filtro para o Personal saber quem falta treinar (Requisito 3 do menu)
    public void verFaltasDoDia(LocalDate data) {
        try {
            System.out.println("\n--- ALUNOS QUE AINDA NÃO COMPARECERAM (" + data + ") ---");
            boolean temFalta = false;
            for (Aula a : registros) {
                if (a.getData().equals(data) && !a.isCompareceu()) {
                    System.out.println("- " + a.getAluno().getNome() + " às " + a.getHorario());
                    temFalta = true;
                }
            }
            if (!temFalta) System.out.println("Nenhuma pendência para esta data!");
        } catch (Exception e) {
            System.out.println("Erro ao verificar faltas: " + e.getMessage());
        }
    }
    //arrumando os comflitos de hora, data e cpf
    public boolean conflitoDeHorario(String cpf, LocalDate data, LocalTime horario) {
        for (Aula a : registros) {
            if (
                    a.getAluno().getCpf().equals(cpf) &&
                            a.getData().equals(data) &&
                            a.getHorario().equals(horario)
            ) {
                return true;
            }
        }
        return false;
    }

    // Getter para o Main poder listar se necessário
    public List<Aula> getRegistros() {
        return registros;
    }

}