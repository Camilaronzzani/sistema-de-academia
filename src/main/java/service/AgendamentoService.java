package service;

import entity.AgendamentoEntity;
import entity.StatusAluno;
import repository.AgendamentoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AgendamentoService {

    AgendamentoRepository agendamentoRepository = new AgendamentoRepository();

    public void agendar(AgendamentoEntity agendamento) {
        if (agendamento.getAluno() == null || agendamento.getAluno().getStatus() != StatusAluno.ATIVO) {
            throw new IllegalArgumentException("Apenas alunos ativos podem fazer agendamentos.");
        }
        LocalDateTime dataHora = agendamento.getDisponibilidade().getData()
                .atTime(agendamento.getDisponibilidade().getHoraInicio());
        if (!dataHora.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Esse horario ja passou.");
        }
        agendamento.setDataHora(dataHora);
        agendamentoRepository.salvar(agendamento);
    }

    public Optional<AgendamentoEntity> buscarPorId(Long id) {
        return agendamentoRepository.buscarPorId(id);
    }

    public List<AgendamentoEntity> listarPorAluno(Long idAluno) {
        return agendamentoRepository.listarPorAluno(idAluno);
    }

    public List<AgendamentoEntity> listarPorAlunoHoje(Long idAluno, LocalDate hoje) {
        return agendamentoRepository.listarPorAlunoHoje(idAluno, hoje);
    }

    public List<AgendamentoEntity> listarPorPersonal(Long idPersonal) {
        return agendamentoRepository.listarPorPersonal(idPersonal);
    }

    public List<AgendamentoEntity> listarSemCheckin(Long idAluno) {
        return agendamentoRepository.listarSemCheckin(idAluno);
    }
}
