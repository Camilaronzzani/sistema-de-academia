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
    DiarioDeTreino diario = new DiarioDeTreino();
    private int opcao;

    public void menuPrincipal(){
        Login login = new Login();

        if(!login.autenticar()){
            return;
        }
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
                opcao = 0; //add pois dava erro de pegar o comando anterior
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
        System.out.println("digite o cpf");
        String cpf = scanner.nextLine();


        LocalDate data = null; // Começa vazia
        while (data == null) {
            try {
                System.out.print("Data da aula (dd/mm/aaaa): ");
                String dataStr = scanner.nextLine();

                LocalDate dataDigitada = LocalDate.parse(dataStr, formatadorData); //Se usuario digitar letra ou  formato errado
                if (dataDigitada.isBefore(LocalDate.now())) {
                    System.out.println("A data tem que ser a partir de hoje!");
                } else {
                    data = dataDigitada; //Só aceita a data de hoje ou no futuro
                }
            }catch (Exception e) {
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

        Aluno aluno = new Aluno(nome, cpf);

        //mesma data, horario e cpf = cancela
        if (diario.conflitoDeHorario(cpf, data, horario)) {
            System.out.println("Esse aluno já tem aula nesse horário!");
            return;
        }

        // Criação do agendamento
        Aula novaAula = new Aula(aluno, data, horario);

        // Chama o "anotarNovoTreino"
        diario.anotarNovoTreino(novaAula);
    }

    private void opcaoDois(){
        System.out.print("Digite o nome do aluno para confirmar presença: ");
        String nomePresenca = scanner.nextLine();

        LocalDate dataPresenca = null;
        while (dataPresenca == null) {
            try {
                System.out.print("Data da aula (dd/mm/aaaa): ");
                String dataStr = scanner.nextLine();
                dataPresenca = LocalDate.parse(dataStr, formatadorData);
            } catch (Exception e) {
                System.out.println("Erro: Use apenas números e barras.");
            }
        }

        diario.marcarPresencaNoDiario(nomePresenca, dataPresenca);
    }

    private void opcaoTres(){
        diario.exibirTodosOsTreinos();

        System.out.println("\nConsultar faltas de qual data?");
        System.out.println("1 - Hoje (" + LocalDate.now().format(formatadorData) + ")");
        System.out.println("2 - Outra data");
        System.out.print("Escolha: ");

        LocalDate dataFaltas = LocalDate.now();
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha == 2) {
                dataFaltas = null;
                while (dataFaltas == null) {
                    try {
                        System.out.print("Data (dd/mm/aaaa): ");
                        String dataStr = scanner.nextLine();
                        dataFaltas = LocalDate.parse(dataStr, formatadorData);
                    } catch (Exception e) {
                        System.out.println("Erro: Use apenas números e barras.");
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida! Use apenas números.");
            scanner.nextLine();
        }

        diario.verFaltasDoDia(dataFaltas);
    }
}
