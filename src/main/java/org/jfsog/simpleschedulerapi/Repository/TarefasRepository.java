package org.jfsog.simpleschedulerapi.Repository;

import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {
    @Override
    List<Tarefa> findAll();
    default List<Tarefa> findConflitos(
            @Param("dataHoraInicio") LocalDateTime dataHoraInicio, @Param("dataHoraFim") LocalDateTime dataHoraFim) {
        return findAll().stream()
                .filter(e -> !(e.getData().isAfter(dataHoraFim) ||
                               dataHoraInicio.isAfter(e.getData().plusMinutes(e.getDuracao()))))
                .toList();
    }
}
