package repository;

import config.HibernateUtil;
import entity.DiarioDeTreinoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

    public class DiarioDeTreinoRepository implements Repositorio<DiarioDeTreinoEntity, Long> {

        public void salvar(DiarioDeTreinoEntity diarioEntity) {
            Transaction tx = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                session.save(diarioEntity);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }

        public Optional<DiarioDeTreinoEntity> buscarPorId(Long id) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return Optional.ofNullable(session.get(DiarioDeTreinoEntity.class, id));
            }
        }

        public List<DiarioDeTreinoEntity> listarPorAluno(Long idAluno) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.createQuery("FROM DiarioDeTreinoEntity WHERE aluno.id = :idAluno", DiarioDeTreinoEntity.class)
                        .setParameter("idAluno", idAluno)
                        .list();
            }
        }

        public List<DiarioDeTreinoEntity> listarTodos() {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.createQuery("FROM DiarioDeTreinoEntity", DiarioDeTreinoEntity.class).list();
            }
        }

        public void atualizar(DiarioDeTreinoEntity diarioEntity) {
            Transaction tx = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                session.update(diarioEntity);
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
                DiarioDeTreinoEntity diarioEntity = session.get(DiarioDeTreinoEntity.class, id);
                if (diarioEntity != null) session.delete(diarioEntity);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

