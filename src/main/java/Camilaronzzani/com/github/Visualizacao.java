package Camilaronzzani.com.github;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Visualizacao {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
    Login.DiarioDeTreino diario = new Login.DiarioDeTreino();
    private int opcao;

    public void menuPrincipal(){
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
                System.out.println("Caracter inválido");
                scanner.nextLine(); // Limpa o buffer do scanner
            }


            switch (opcao) {
                case 1:
                    this.opcaoUm();
                    break;

                case 2:
                    this.opcaoDois();
                    break;

                case 3:
                    this.opcaoTres();
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


    private void opcaoUm(){
        // Entrada de dados para criar o Aluno e a Aula
        System.out.print("Nome do Aluno: ");
        String nome = scanner.nextLine();
        Aluno aluno = new Aluno(nome);

        LocalDate data = null; // Começa vazia
        while (data == null) {
            try {
                System.out.print("Data da aula (dd/mm/aaaa): ");
                String dataStr = scanner.nextLine();

                data = LocalDate.parse(dataStr, formatadorData); //Se usuario digitar letra ou  formato errado

            } catch (Exception e) {
                System.out.println(" Erro: Use apenas números e barras.");
            }
        }

        LocalTime horario = null; // Começa com nulo para loop iniciar
        while (horario == null) {
            try {
                System.out.print("Horário da aula (hh:mm): ");
                String horaStr = scanner.nextLine();

                //Tenta converter a String para LocalTime usando seu formatador
                horario = LocalTime.parse(horaStr, formatadorHora);
            } catch (Exception e) {   //Se o usuário digitar "abc" ou "1400", o código cai aqui.
                System.out.println("Formato inválido!");
            }
        }

        // Criação do agendamento
        Aula novaAula = new Aula(aluno, data, horario);

        // Chama o "anotarNovoTreino"
        diario.anotarNovoTreino(novaAula);
    }

    private void opcaoDois(){
        System.out.print("Digite o nome do aluno para confirmar presença: ");
        String nomePresenca = scanner.nextLine();

        // Chama o "marcarPresencaNoDiario"
        diario.marcarPresencaNoDiario(nomePresenca);
    }

    private void opcaoTres(){
        // Mostra o diário completo com o check [V] ou [] que você criou
        diario.exibirTodosOsTreinos();

        // Filtro adicional para faltas de hoje
        System.out.println("\n--- Resumo de Faltas de Hoje (" + LocalDate.now().format(formatadorData) + ") ---");
        diario.verFaltasDoDia(LocalDate.now());
    }
}
