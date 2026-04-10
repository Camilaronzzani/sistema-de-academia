package service;

import config.HibernateUtil;
import entity.AlunoEntity;
import entity.TipoUsuario;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

public class AlunoService {

    AlunoRepository alunoRepository = new AlunoRepository();

    public void cadastrar(AlunoEntity aluno, String senha) {
        Validador.validarNome(aluno.getNome());
        Validador.validarCpf(aluno.getCpf());
        Validador.validarTelefone(aluno.getTelefone());
        Validador.validarEmail(aluno.getEmail());
        Validador.validarDataNascimento(aluno.getDataNascimento());
        Validador.validarDataMatricula(aluno.getDataMatricula());
        Validador.validarSenha(senha);
        if (alunoRepository.buscarPorCpf(aluno.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF " + aluno.getCpf() + " ja esta cadastrado.");
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(aluno);
            UserEntity user = new UserEntity(senha, TipoUsuario.ALUNO);
            user.setAluno(aluno);
            session.save(user);
            tx.commit();
        }
    }

    public Optional<AlunoEntity> buscarPorCpf(String cpf) {
        return alunoRepository.buscarPorCpf(cpf);
    }

    public Optional<AlunoEntity> buscarPorId(Long id) {
        return alunoRepository.buscarPorId(id);
    }

    public List<AlunoEntity> listarTodos() {
        return alunoRepository.listarTodos();
    }
}
