package org.jfsog.simpleschedulerapi.Carga;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.jfsog.simpleschedulerapi.Service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Component
@Slf4j
public class RepositorioTeste implements ApplicationRunner {
    private final Faker faker = Faker.instance(Locale.getDefault());
    @Autowired
    private TarefaService tarefaService;
    @Override
    public void run(ApplicationArguments args) {
        Stream.iterate(0, i -> i + 1)
              .limit(10)
              .map(i -> criaTarefaFake())
              .peek(t -> log.info("Salvando task: {}", t))
              .toList()
              .forEach(tarefaService::salvar);
    }
    private Tarefa criaTarefaFake() {
        var time = LocalDateTime.ofInstant(faker.date().future(7, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault());
        // aqui a descrição pode ser qualquer coisa
        var desc=faker.name().name();
        return new Tarefa(null, time,desc , faker.number().numberBetween(1L, 4L) * 30);
    }
}
