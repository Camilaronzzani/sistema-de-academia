package service;

import entity.CheckinEntity;
import repository.CheckinRepository;

import java.util.List;
import java.util.Optional;

public class CheckinService {

    CheckinRepository checkinRepository = new CheckinRepository();

    public void registrar(CheckinEntity checkin) {
        if (checkin.getAgendamento() == null) {
            throw new IllegalArgumentException("Agendamento e obrigatorio para o checkin.");
        }
        if (checkin.getAluno() == null) {
            throw new IllegalArgumentException("Aluno e obrigatorio para o checkin.");
        }
        checkinRepository.salvar(checkin);
    }

    public Optional<CheckinEntity> buscarPorId(Long id) {
        return checkinRepository.buscarPorId(id);
    }

    public List<CheckinEntity> listarPorAluno(Long idAluno) {
        return checkinRepository.listarPorAluno(idAluno);
    }
}
