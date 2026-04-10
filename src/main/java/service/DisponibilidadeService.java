package service;

import entity.DisponibilidadeEntity;
import repository.DisponibilidadeRepository;

import java.util.List;
import java.util.Optional;

public class DisponibilidadeService {

    DisponibilidadeRepository disponibilidadeRepository = new DisponibilidadeRepository();

    public void cadastrar(DisponibilidadeEntity disponibilidade) {
        if (disponibilidade.getHoraInicio().isAfter(disponibilidade.getHoraFim())) {
            throw new IllegalArgumentException("Hora de inicio nao pode ser depois da hora de fim.");
        }
        disponibilidadeRepository.salvar(disponibilidade);
    }

    public Optional<DisponibilidadeEntity> buscarPorId(Long id) {
        return disponibilidadeRepository.buscarPorId(id);
    }

    public List<DisponibilidadeEntity> listarTodos() {
        return disponibilidadeRepository.listarTodos();
    }

    public List<DisponibilidadeEntity> listarPorPersonal(Long idPersonal) {
        return disponibilidadeRepository.listarPorPersonal(idPersonal);
    }
}
