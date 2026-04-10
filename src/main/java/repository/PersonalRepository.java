package repository;

import config.HibernateUtil;
import entity.PersonalEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PersonalRepository implements Repositorio<PersonalEntity, Long> {

    public void salvar(PersonalEntity personal) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(personal);
            tx.commit();
        }
    }

    public Optional<PersonalEntity> buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(PersonalEntity.class, id));
        }
    }

    public Optional<PersonalEntity> buscarPorCpf(String cpf) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM PersonalEntity WHERE cpf = :cpf", PersonalEntity.class)
                    .setParameter("cpf", cpf)
                    .uniqueResultOptional();
        }
    }

    public List<PersonalEntity> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM PersonalEntity", PersonalEntity.class).list();
        }
    }

    public boolean existeAlgumPersonal() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery("SELECT COUNT(p) FROM PersonalEntity p", Long.class)
                    .uniqueResult();
            return count != null && count > 0;
        }
    }

    public void atualizar(PersonalEntity personal) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(personal);
            tx.commit();
        }
    }

    public void deletar(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            PersonalEntity personal = session.get(PersonalEntity.class, id);
            if (personal != null) session.delete(personal);
            tx.commit();
        }
    }
}
