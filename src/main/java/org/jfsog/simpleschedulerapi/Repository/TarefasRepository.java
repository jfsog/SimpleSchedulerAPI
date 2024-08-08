package org.jfsog.simpleschedulerapi.Repository;

import jakarta.annotation.Nonnull;
import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {
    @Override
    List<Tarefa> findAll();
    @Query(
            value = "SELECT * FROM tarefa t WHERE NOT (t.data_hora_inicio > :dataHoraFim OR " +
                    "DATEADD('MINUTE', t.duracao, t.data_hora_inicio) < :dataHoraInicio)", nativeQuery = true
    )
    List<Tarefa> tarefasComConflitos(
            @Param("dataHoraInicio") LocalDateTime dataHoraInicio, @Param("dataHoraFim") LocalDateTime dataHoraFim);
    @Override
    void deleteById(@Param("id") @Nonnull Long id);
}
