package org.jfsog.simpleschedulerapi.Carga;

import lombok.extern.slf4j.Slf4j;
import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.jfsog.simpleschedulerapi.Repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class RepositorioTeste implements ApplicationRunner {
    @Autowired
    private TarefasRepository tarefasRepository;
    @Override
    public void run(ApplicationArguments args) {
        var task = new Tarefa(null, LocalDateTime.now(), "teste", 30L);
        var task1 = new Tarefa(null, LocalDateTime.now(), "test2", 30L);
        log.info("Salvando task: {}", task);
        tarefasRepository.saveAll(List.of(task, task1));
    }
}
