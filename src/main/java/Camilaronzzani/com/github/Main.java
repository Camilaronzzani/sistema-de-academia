package Camilaronzzani.com.github;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DiarioDeTreino diario = new DiarioDeTreino();

        // Ler a data e hora do teclado
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n--- MENU PERSONAL TRAINER ---");
            System.out.println("1 - Agendar Aula (Cadastrar Aluno/Horário)");
            System.out.println("2 - Registrar Presença");
            System.out.println("3 - Ver Agenda Geral e Faltas");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            try{
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner
            }catch (InputMismatchException e){
                System.out.println("Caracter invalido");
                scanner.nextLine(); // Limpa o buffer do scanner
            }



            switch (opcao) {
                case 1:
                    // Entrada de dados para criar o Aluno e a Aula
                    System.out.print("Nome do Aluno: ");
                    String nome = scanner.nextLine();
                    Aluno aluno = new Aluno(nome);

                    System.out.print("Data da aula (dd/mm/aaaa): ");
                    String dataStr = scanner.nextLine();
                    LocalDate data = LocalDate.parse(dataStr, formatadorData);

                    System.out.print("Horário da aula (hh:mm): ");
                    String horaStr = scanner.nextLine();
                    LocalTime horario = LocalTime.parse(horaStr, formatadorHora);

                    // Criação do agendamento
                    Aula novaAula = new Aula(aluno, data, horario);

                    // Chama o "anotarNovoTreino"
                    diario.anotarNovoTreino(novaAula);
                    break;

                case 2:
                    System.out.print("Digite o nome do aluno para confirmar presença: ");
                    String nomePresenca = scanner.nextLine();

                    // Chama o "marcarPresencaNoDiario"
                    diario.marcarPresencaNoDiario(nomePresenca);
                    break;

                case 3:
                    // Mostra o diário completo com o check [V] ou [] que você criou
                    diario.exibirTodosOsTreinos();

                    // Filtro adicional para faltas de hoje
                    System.out.println("\n--- Resumo de Faltas de Hoje (" + LocalDate.now().format(formatadorData) + ") ---");
                    diario.verFaltasDoDia(LocalDate.now());
                    break;

                case 4:
                    System.out.println("Encerrando o programa. Bom treino!");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        scanner.close();
    }
}