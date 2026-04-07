package repository;

import config.HibernateUtil;
import entity.AlunoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AlunoRepository {

    public void salvar(AlunoEntity alunoEntity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(alunoEntity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
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

    public void atualizar(AlunoEntity alunoEntity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(alunoEntity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void deletar(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            AlunoEntity alunoEntity = session.get(AlunoEntity.class, id);
            if (alunoEntity != null) session.delete(alunoEntity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
