package Camilaronzzani.com.github;

import entity.*;
import service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

public class Visualizacao {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    AlunoService alunoService = new AlunoService();
    PersonalService personalService = new PersonalService();
    UserService userService = new UserService();
    AgendamentoService agendamentoService = new AgendamentoService();
    DisponibilidadeService disponibilidadeService = new DisponibilidadeService();
    CheckinService checkinService = new CheckinService();

    public void menuPrincipal() {
        while (true) {
            System.out.println("\n=== SISTEMA ACADEMIA ===");

            if (!personalService.existeAlgumPersonal()) {
                System.out.println("Nenhum personal cadastrado no sistema.");
                System.out.println("1 - Cadastrar primeiro Personal");
                System.out.println("2 - Sair");
                System.out.print("Escolha: ");

                int opcao = lerInt();
                if (opcao == 1) {
                    cadastrarPersonal();
                } else if (opcao == 2) {
                    System.out.println("Encerrando.");
                    break;
                } else {
                    System.out.println("Opcao invalida");
                }
            } else {
                System.out.println("1 - Sou Aluno");
                System.out.println("2 - Sou Personal");
                System.out.println("3 - Cadastrar Personal");
                System.out.println("4 - Sair");
                System.out.print("Escolha: ");

                int opcao = lerInt();
                if (opcao == 1) {
                    AlunoEntity aluno = loginAluno();
                    if (aluno != null) menuAluno(aluno);
                } else if (opcao == 2) {
                    PersonalEntity personal = loginPersonal();
                    if (personal != null) menuPersonal(personal);
                } else if (opcao == 3) {
                    System.out.println("\nPara cadastrar um novo personal, faca login com um personal existente.");
                    PersonalEntity personal = loginPersonal();
                    if (personal != null) cadastrarPersonal();
                } else if (opcao == 4) {
                    System.out.println("Encerrando.");
                    break;
                } else {
                    System.out.println("Opcao invalida");
                }
            }
        }
        scanner.close();
    }

    private boolean autenticarUser(Optional<UserEntity> optUser, String mensagemSemAcesso) {
        if (optUser.isEmpty()) {
            System.out.println(mensagemSemAcesso);
            return false;
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        try {
            if (!userService.autenticar(optUser.get(), senha)) {
                System.out.println("Senha incorreta.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private AlunoEntity loginAluno() {
        System.out.print("\nCPF: ");
        String cpf = scanner.nextLine();
        Optional<AlunoEntity> resultado = alunoService.buscarPorCpf(cpf);
        if (resultado.isEmpty()) { System.out.println("Aluno nao encontrado."); return null; }
        AlunoEntity aluno = resultado.get();
        if (!autenticarUser(userService.buscarPorIdAluno(aluno.getId()), "Acesso nao configurado. Fale com seu personal.")) return null;
        System.out.println("Bem-vindo, " + aluno.getNome() + "!");
        return aluno;
    }

    private PersonalEntity loginPersonal() {
        System.out.print("\nCPF: ");
        String cpf = scanner.nextLine();
        Optional<PersonalEntity> resultado = personalService.buscarPorCpf(cpf);
        if (resultado.isEmpty()) { System.out.println("Personal nao encontrado."); return null; }
        PersonalEntity personal = resultado.get();
        if (!autenticarUser(userService.buscarPorIdPersonal(personal.getId()), "Acesso nao configurado.")) return null;
        System.out.println("Bem-vindo, " + personal.getNome() + "!");
        return personal;
    }

    private void menuAluno(AlunoEntity aluno) {
        while (true) {
            System.out.println("\n--- MENU ALUNO ---");
            System.out.println("1 - Ver horarios disponiveis");
            System.out.println("2 - Fazer agendamento");
            System.out.println("3 - Meus agendamentos");
            System.out.println("4 - Meu treino");
            System.out.println("5 - Fazer checkin");
            System.out.println("6 - Agendamentos pendentes");
            System.out.println("7 - Sair");
            System.out.print("Escolha: ");

            switch (lerInt()) {
                case 1: verHorariosDisponiveis(); break;
                case 2: fazerAgendamento(aluno); break;
                case 3: meusAgendamentos(aluno); break;
                case 4: meuTreino(aluno); break;
                case 5: checkinAluno(aluno); break;
                case 6: agendamentosPendentes(aluno); break;
                case 7: System.out.println("Ate logo!"); return;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private void menuPersonal(PersonalEntity personal) {
        while (true) {
            System.out.println("\n--- MENU PERSONAL ---");
            System.out.println("1 - Cadastrar disponibilidade");
            System.out.println("2 - Ver minhas disponibilidades");
            System.out.println("3 - Cadastrar aluno");
            System.out.println("4 - Listar alunos");
            System.out.println("5 - Registrar evolucao de aluno");
            System.out.println("6 - Criar treino para aluno");
            System.out.println("7 - Ver treinos");
            System.out.println("8 - Fazer checkin de aluno");
            System.out.println("9 - Ver agendamentos dos meus alunos");
            System.out.println("10 - Sair");
            System.out.print("Escolha: ");

            switch (lerInt()) {
                case 1: cadastrarDisponibilidade(personal); break;
                case 2: verMinhasDisponibilidades(personal); break;
                case 3: cadastrarAluno(); break;
                case 4: listarAlunos(); break;
                case 5: registrarEvolucao(personal); break;
                case 6: criarTreino(personal); break;
                case 7: verTreinos(); break;
                case 8: checkinDeAluno(); break;
                case 9: verAgendamentosDoPersonal(personal); break;
                case 10: System.out.println("Ate logo!"); return;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private void verHorariosDisponiveis() {
        List<DisponibilidadeEntity> lista = disponibilidadeService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum horario disponivel no momento");
            return;
        }
        System.out.println("\n--- Horarios Disponiveis ---");
        for (DisponibilidadeEntity d : lista) {
            System.out.println("ID: " + d.getId()
                    + " | Personal: " + d.getPersonal().getNome()
                    + " | Dia: " + d.getDiaSemana()
                    + " | Horario: " + d.getHoraInicio() + " - " + d.getHoraFim());
        }
    }

    private void fazerAgendamento(AlunoEntity aluno) {
        System.out.println("\n--- Novo Agendamento ---");
        verHorariosDisponiveis();

        System.out.print("ID da disponibilidade escolhida: ");
        Long idDisp = lerLong();
        Optional<DisponibilidadeEntity> optDisp = disponibilidadeService.buscarPorId(idDisp);
        if (optDisp.isEmpty()) {
            System.out.println("Disponibilidade nao encontrada");
            return;
        }

        LocalDateTime dataHora = lerDataHora("Data e hora do agendamento (dd/MM/aaaa HH:mm): ");
        AgendamentoEntity agendamento = new AgendamentoEntity(aluno, optDisp.get(), dataHora);

        try {
            agendamentoService.agendar(agendamento);
            System.out.println("Agendamento realizado com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao realizar agendamento: " + e.getMessage());
        }
    }

    private void meusAgendamentos(AlunoEntity aluno) {
        List<AgendamentoEntity> lista = agendamentoService.listarPorAluno(aluno.getId());
        if (lista.isEmpty()) {
            System.out.println("Voce nao possui agendamentos.");
            return;
        }
        System.out.println("\n--- Meus Agendamentos ---");
        for (AgendamentoEntity ag : lista) {
            System.out.println("ID: " + ag.getId()
                    + " | Data/Hora: " + ag.getDataHora().format(formatadorDataHora)
                    + " | Status: " + ag.getStatus().getDescricao());
        }
    }

    private void agendamentosPendentes(AlunoEntity aluno) {
        List<AgendamentoEntity> lista = agendamentoService.listarSemCheckin(aluno.getId());
        if (lista.isEmpty()) {
            System.out.println("Sem agendamentos pendentes.");
            return;
        }
        System.out.println("\n--- Agendamentos sem checkin ---");
        for (AgendamentoEntity ag : lista) {
            System.out.println("ID: " + ag.getId()
                    + " | Data/Hora: " + ag.getDataHora().format(formatadorDataHora)
                    + " | Status: " + ag.getStatus().getDescricao());
        }
    }

    private void meuTreino(AlunoEntity aluno) {
        System.out.println("Funcionalidade em desenvolvimento.");
    }

    private void checkinAluno(AlunoEntity aluno) {
        System.out.println("\n--- Checkin ---");
        exibirMenuCheckin();
        int opcao = lerInt();
        if (opcao == 1) fazerCheckinSistema(aluno);
        else if (opcao == 2) System.out.println("Reconhecimento facial sera implementado em breve.");
        else System.out.println("Opcao invalida!");
    }

    private void cadastrarDisponibilidade(PersonalEntity personal) {
        System.out.println("\n--- Nova Disponibilidade ---");
        DiaSemana dia = lerDiaSemana();

        java.time.LocalTime inicio;
        java.time.LocalTime fim;
        while (true) {
            inicio = lerHorario("Hora de inicio (HH:mm): ");
            fim = lerHorario("Hora de fim (HH:mm): ");
            if (!inicio.isBefore(fim)) {
                System.out.println("Hora de inicio nao pode ser igual ou depois da hora de fim. Tente novamente.");
            } else {
                break;
            }
        }

        try {
            DisponibilidadeEntity disp = new DisponibilidadeEntity(personal, dia, inicio, fim);
            disponibilidadeService.cadastrar(disp);
            System.out.println("Disponibilidade cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void verMinhasDisponibilidades(PersonalEntity personal) {
        List<DisponibilidadeEntity> lista = disponibilidadeService.listarPorPersonal(personal.getId());
        if (lista.isEmpty()) {
            System.out.println("Voce nao possui disponibilidades cadastradas.");
            return;
        }
        System.out.println("\n--- Minhas Disponibilidades ---");
        for (DisponibilidadeEntity d : lista) {
            System.out.println("ID: " + d.getId()
                    + " | Dia: " + d.getDiaSemana()
                    + " | Horario: " + d.getHoraInicio() + " - " + d.getHoraFim());
        }
    }

    private void cadastrarPersonal() {
        System.out.println("\n--- Cadastrar Personal ---");

        String nome = lerCampoValidado("Nome: ", Validador::validarNome);
        String cpf = lerCampoValidado("CPF (somente numeros, 11 digitos): ", Validador::validarCpf);
        String cref = lerCampoValidado("CREF (ex: 123456-G/SP): ", Validador::validarCref);
        String especialidade = lerCampoOpcionalValidado("Especialidade (Enter para pular): ", Validador::validarNome);
        String telefone = lerCampoOpcionalValidado("Telefone (Enter para pular): ", Validador::validarTelefone);
        String email = lerCampoOpcionalValidado("Email (Enter para pular): ", Validador::validarEmail);
        String senha = lerCampoValidado("Senha (minimo 6 caracteres): ", Validador::validarSenha);

        PersonalEntity personal = new PersonalEntity(nome, cpf, cref);
        if (especialidade != null) personal.setEspecialidade(especialidade);
        if (telefone != null) personal.setTelefone(telefone);
        if (email != null) personal.setEmail(email);

        try {
            personalService.cadastrar(personal, senha);
            System.out.println("Personal cadastrado com sucesso! Agora faca login.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarAluno() {
        System.out.println("\n--- Cadastrar Aluno ---");

        String nome = lerCampoValidado("Nome: ", Validador::validarNome);
        String cpf = lerCampoValidado("CPF (somente numeros, 11 digitos): ", Validador::validarCpf);
        LocalDate dataNascimento = lerDataValidada("Data de nascimento (dd/MM/aaaa): ", Validador::validarDataNascimento);
        LocalDate dataMatricula = lerDataValidada("Data de matricula (dd/MM/aaaa): ", Validador::validarDataMatricula);
        String telefone = lerCampoOpcionalValidado("Telefone (Enter para pular): ", Validador::validarTelefone);
        String email = lerCampoOpcionalValidado("Email (Enter para pular): ", Validador::validarEmail);
        String senha = lerCampoValidado("Senha do aluno (minimo 6 caracteres): ", Validador::validarSenha);

        AlunoEntity aluno = new AlunoEntity(nome, cpf, dataNascimento, dataMatricula);
        if (telefone != null) aluno.setTelefone(telefone);
        if (email != null) aluno.setEmail(email);

        try {
            alunoService.cadastrar(aluno, senha);
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarAlunos() {
        List<AlunoEntity> lista = alunoService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Alunos ---");
        for (AlunoEntity a : lista) {
            System.out.println("ID: " + a.getId()
                    + " | Nome: " + a.getNome()
                    + " | CPF: " + a.getCpf()
                    + " | Status: " + a.getStatus());
        }
    }

    private void registrarEvolucao(PersonalEntity personal) {
        System.out.println("em desenvolvimento.");
    }

    private void criarTreino(PersonalEntity personal) {
        System.out.println("em desenvolvimento.");
    }

    private void verTreinos() {
        System.out.println("em desenvolvimento.");
    }

    private void checkinDeAluno() {
        System.out.println("\n--- Checkin de Aluno ---");
        exibirMenuCheckin();
        int opcao = lerInt();
        if (opcao == 1) {
            System.out.print("CPF do aluno: ");
            String cpf = scanner.nextLine();
            Optional<AlunoEntity> optAluno = alunoService.buscarPorCpf(cpf);
            if (optAluno.isEmpty()) { System.out.println("Aluno nao encontrado"); return; }
            fazerCheckinSistema(optAluno.get());
        } else if (opcao == 2) {
            System.out.println("Reconhecimento facial ainda sera implantado(eu acho)");
        } else {
            System.out.println("Opcao invalida");
        }
    }

    private void verAgendamentosDoPersonal(PersonalEntity personal) {
        List<AgendamentoEntity> lista = agendamentoService.listarPorPersonal(personal.getId());
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado");
            return;
        }
        System.out.println("\n--- Agendamentos dos Alunos --");
        for (AgendamentoEntity ag : lista) {
            System.out.println("ID: " + ag.getId()
                    + " | Aluno: " + ag.getAluno().getNome()
                    + " | Data/Hora: " + ag.getDataHora().format(formatadorDataHora)
                    + " | Status: " + ag.getStatus().getDescricao());
        }
    }

    private void exibirMenuCheckin() {
        System.out.println("1 - Checkin pelo sistema");
        System.out.println("2 - Checkin por Face ID");
        System.out.print("Escolha: ");
    }

    private void fazerCheckinSistema(AlunoEntity aluno) {
        List<AgendamentoEntity> deHoje = agendamentoService.listarPorAlunoHoje(aluno.getId(), LocalDate.now());
        if (deHoje.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado para hoje.");
            return;
        }
        System.out.println("Agendamentos de hoje para " + aluno.getNome() + ":");
        for (AgendamentoEntity ag : deHoje) {
            System.out.println("ID: " + ag.getId() + " | Horario: " + ag.getDataHora().format(formatadorDataHora));
        }
        System.out.print("ID do agendamento: ");
        Long idAg = lerLong();
        Optional<AgendamentoEntity> optAg = agendamentoService.buscarPorId(idAg);
        if (optAg.isEmpty()) { System.out.println("Agendamento nao encontrado."); return; }
        CheckinEntity checkin = new CheckinEntity(optAg.get(), aluno);
        checkin.setMetodo(MetodoCheckin.SISTEMA);
        try {
            checkinService.registrar(checkin);
            System.out.println("Checkin de " + aluno.getNome() + " confirmado!");
        } catch (Exception e) {
            System.out.println("Erro ao registrar checkin: " + e.getMessage());
        }
    }

    private String lerCampoValidado(String mensagem, Consumer<String> validador) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine();
            try {
                validador.accept(valor);
                return valor;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private String lerCampoOpcionalValidado(String mensagem, Consumer<String> validador) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine();
            if (valor.isBlank()) return null;
            try {
                validador.accept(valor);
                return valor;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private int lerInt() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private Long lerLong() {
        try {
            Long valor = scanner.nextLong();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1L;
        }
    }

    private DiaSemana lerDiaSemana() {
        while (true) {
            System.out.print("Dia da semana (SEGUNDA / TERCA / QUARTA / QUINTA / SEXTA / SABADO / DOMINGO): ");
            String valor = scanner.nextLine().trim().toUpperCase();
            try {
                return DiaSemana.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Dia invalido. Digite exatamente um dos valores indicados.");
            }
        }
    }

    private java.time.LocalTime lerHorario(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            try {
                return java.time.LocalTime.parse(valor);
            } catch (Exception e) {
                System.out.println("Horario invalido. Use o formato HH:mm (ex: 08:30).");
            }
        }
    }

    private LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return LocalDate.parse(scanner.nextLine(), formatadorData);
            } catch (Exception e) {
                System.out.println("Formato invalido! Use dd/MM/aaaa.");
            }
        }
    }

    private LocalDate lerDataValidada(String mensagem, Consumer<LocalDate> validador) {
        while (true) {
            try {
                System.out.print(mensagem);
                LocalDate data = LocalDate.parse(scanner.nextLine(), formatadorData);
                try {
                    validador.accept(data);
                    return data;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Formato invalido! Use dd/MM/aaaa.");
            }
        }
    }

    private LocalDateTime lerDataHora(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return LocalDateTime.parse(scanner.nextLine(), formatadorDataHora);
            } catch (Exception e) {
                System.out.println("Formato invalido! Use dd/MM/aaaa HH:mm.");
            }
        }
    }
}
