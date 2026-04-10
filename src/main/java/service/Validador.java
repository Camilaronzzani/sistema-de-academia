package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Validador {

    public static void validarDataNascimento(LocalDate data) {
        LocalDate hoje = LocalDate.now();
        if (!data.isBefore(hoje)) {
            throw new IllegalArgumentException("Data de nascimento deve ser no passado.");
        }
        if (data.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException("Data de nascimento invalida.");
        }
        if (ChronoUnit.YEARS.between(data, hoje) < 10) {
            throw new IllegalArgumentException("Aluno deve ter pelo menos 10 anos.");
        }
    }

    public static void validarDataMatricula(LocalDate data) {
        if (data.isBefore(LocalDate.of(1950, 1, 1))) {
            throw new IllegalArgumentException("Data de matricula invalida.");
        }
        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de matricula nao pode ser no futuro.");
        }
    }

    public static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio.");
        }
        if (!nome.matches("[A-Za-zÀ-ÿ\\s]+")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras e espacos.");
        }
    }

    public static void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF nao pode ser vazio.");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 digitos numericos (sem pontos ou traco).");
        }
    }

    public static void validarTelefone(String telefone) {
        if (telefone != null && !telefone.isBlank()) {
            if (!telefone.matches("\\d{10,11}")) {
                throw new IllegalArgumentException("Telefone deve conter apenas numeros com 10 ou 11 digitos.");
            }
        }
    }

    public static void validarEmail(String email) {
        if (email != null && !email.isBlank()) {
            if (!email.matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("Email invalido.");
            }
        }
    }

    public static void validarSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha nao pode ser vazia.");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
        }
    }

    public static void validarCref(String cref) {
        if (cref == null || cref.isBlank()) {
            throw new IllegalArgumentException("CREF nao pode ser vazio.");
        }
        if (!cref.matches("\\d{6}-[A-Z]/[A-Z]{2}")) {
            throw new IllegalArgumentException("CREF invalido. Formato esperado: 123456-G/SP");
        }
    }
}
