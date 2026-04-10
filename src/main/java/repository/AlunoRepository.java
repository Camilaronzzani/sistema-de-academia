package repository;

import config.HibernateUtil;
import entity.AlunoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AlunoRepository implements Repositorio<AlunoEntity, Long> {

    public void salvar(AlunoEntity aluno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(aluno);
            tx.commit();
        }
    }

    public Optional<AlunoEntity> buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(AlunoEntity.class, id));
        }
    }

    public Optional<AlunoEntity> buscarPorCpf(String cpf) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM AlunoEntity WHERE cpf = :cpf", AlunoEntity.class)
                    .setParameter("cpf", cpf)
                    .uniqueResultOptional();
        }
    }

    public List<AlunoEntity> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM AlunoEntity", AlunoEntity.class).list();
        }
    }

    public void atualizar(AlunoEntity aluno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(aluno);
            tx.commit();
        }
    }

    public void deletar(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            AlunoEntity aluno = session.get(AlunoEntity.class, id);
            if (aluno != null) {
                session.delete(aluno);
            }
            tx.commit();
        }
    }
}
