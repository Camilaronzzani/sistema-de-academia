package Camilaronzzani.com.github;

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
        registros.add(aula);
        System.out.println("\n--- AGENDAMENTO REALIZADO ---");
        System.out.println("Aluno: " + aula.getAluno().getNome() + " | Data: " + formatadorData.format(aula.getData()));
    }

    //  Exibir todos os registros (O seu "DIÁRIO DE TREINOS")
    public void exibirTodosOsTreinos() {
        if (registros.isEmpty()) {
            System.out.println("\nO diário está vazio.");
            return;
        }
        System.out.println("\n--- DIÁRIO DE TREINOS COMPLETO ---");
        for (Aula a : registros) {
            String check = a.isCompareceu() ? "[V] TREINOU" : "[] AGENDADO";
            System.out.println(check + " | " + a.getAluno().getNome() + " | " + a.getData() + " às " + a.getHorario());
        }
    }

    // Marcar presença pelo nome
    public void marcarPresencaNoDiario(String nomeAluno) {
        boolean encontrado = false;
        for (Aula a : registros) {
            // Verifica o nome e a aula que não foi marcada como presente
            if (a.getAluno().getNome().equalsIgnoreCase(nomeAluno)) {
                a.setCompareceu(true);
                System.out.println("✓ Presença marcada no diário: " + a.getAluno().getNome());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Aviso: Aluno '" + nomeAluno + "' não encontrado nos agendamentos.");
        }
    }

    // Filtro para o Personal saber quem falta treinar (Requisito 3 do menu)
    public void verFaltasDoDia(LocalDate data) {
        System.out.println("\n--- ALUNOS QUE AINDA NÃO COMPARECERAM (" + data + ") ---");
        boolean temFalta = false;
        for (Aula a : registros) {
            if (a.getData().equals(data) && !a.isCompareceu()) {
                System.out.println("- " + a.getAluno().getNome() + " às " + a.getHorario());
                temFalta = true;
            }
        }
        if (!temFalta) System.out.println("Nenhuma pendência para esta data!");
    }

    // Getter para o Main poder listar se necessário
    public List<Aula> getRegistros() {
        return registros;
    }
}