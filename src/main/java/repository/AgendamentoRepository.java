package repository;

import config.HibernateUtil;
import entity.AgendamentoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AgendamentoRepository implements Repositorio<AgendamentoEntity, Long> {

    public void salvar(AgendamentoEntity agendamento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(agendamento);
            tx.commit();
        }
    }

    public Optional<AgendamentoEntity> buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(AgendamentoEntity.class, id));
        }
    }

    public List<AgendamentoEntity> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM AgendamentoEntity", AgendamentoEntity.class).list();
        }
    }

    public List<AgendamentoEntity> listarPorAluno(Long idAluno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM AgendamentoEntity WHERE aluno.id = :idAluno", AgendamentoEntity.class)
                    .setParameter("idAluno", idAluno)
                    .list();
        }
    }

    public List<AgendamentoEntity> listarPorAlunoHoje(Long idAluno, LocalDate hoje) {
        LocalDateTime inicio = hoje.atStartOfDay();
        LocalDateTime fim = hoje.plusDays(1).atStartOfDay();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM AgendamentoEntity WHERE aluno.id = :idAluno AND dataHora >= :inicio AND dataHora < :fim",
                    AgendamentoEntity.class)
                    .setParameter("idAluno", idAluno)
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim)
                    .list();
        }
    }

    public List<AgendamentoEntity> listarPorPersonal(Long idPersonal) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT a FROM AgendamentoEntity a INNER JOIN a.disponibilidade d WHERE d.personal.id = :idPersonal",
                    AgendamentoEntity.class)
                    .setParameter("idPersonal", idPersonal)
                    .list();
        }
    }

    public List<AgendamentoEntity> listarSemCheckin(Long idAluno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT a FROM AgendamentoEntity a LEFT JOIN a.checkin c WHERE a.aluno.id = :idAluno AND c IS NULL",
                    AgendamentoEntity.class)
                    .setParameter("idAluno", idAluno)
                    .list();
        }
    }

    public void atualizar(AgendamentoEntity agendamento) {//bia
        throw new UnsupportedOperationException("Edicao de agendamentos nao implementada.");
    }

    public void deletar(Long id) { //bia
        throw new UnsupportedOperationException("Exclusao de agendamentos nao implementada.");
    }
}
