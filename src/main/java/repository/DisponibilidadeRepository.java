package repository;

import config.HibernateUtil;
import entity.DisponibilidadeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class DisponibilidadeRepository implements Repositorio<DisponibilidadeEntity, Long> {

    public void salvar(DisponibilidadeEntity disponibilidade) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(disponibilidade);
            tx.commit();
        }
    }

    public Optional<DisponibilidadeEntity> buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(DisponibilidadeEntity.class, id));
        }
    }

    public List<DisponibilidadeEntity> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM DisponibilidadeEntity", DisponibilidadeEntity.class).list();
        }
    }

    public List<DisponibilidadeEntity> listarPorPersonal(Long idPersonal) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM DisponibilidadeEntity WHERE personal.id = :idPersonal", DisponibilidadeEntity.class)
                    .setParameter("idPersonal", idPersonal)
                    .list();
        }
    }

    public void atualizar(DisponibilidadeEntity disponibilidade) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(disponibilidade);
            tx.commit();
        }
    }

    public void deletar(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            DisponibilidadeEntity disponibilidade = session.get(DisponibilidadeEntity.class, id);
            if (disponibilidade != null) session.delete(disponibilidade);
            tx.commit();
        }
    }
}
