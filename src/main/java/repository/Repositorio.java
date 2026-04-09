package repository;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T, ID> {
    void salvar(T entidade);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void atualizar(T entidade);
    void deletar(ID id);
}
