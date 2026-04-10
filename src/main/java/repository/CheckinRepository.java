package repository;

import config.HibernateUtil;
import entity.CheckinEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CheckinRepository implements Repositorio<CheckinEntity, Long> {

    public void salvar(CheckinEntity checkin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(checkin);
            tx.commit();
        }
    }

    public Optional<CheckinEntity> buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(CheckinEntity.class, id));
        }
    }

    public List<CheckinEntity> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM CheckinEntity", CheckinEntity.class).list();
        }
    }

    public List<CheckinEntity> listarPorAluno(Long idAluno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM CheckinEntity WHERE aluno.id = :idAluno", CheckinEntity.class)
                    .setParameter("idAluno", idAluno)
                    .list();
        }
    }

    public void atualizar(CheckinEntity checkin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(checkin);
            tx.commit();
        }
    }

    public void deletar(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            CheckinEntity checkin = session.get(CheckinEntity.class, id);
            if (checkin != null) session.delete(checkin);
            tx.commit();
        }
    }
}
