package org.jfsog.simpleschedulerapi.Controllers;

import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.jfsog.simpleschedulerapi.Repository.TarefasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class AgendamentoController {
    private final TarefasRepository tarefasRepository;
    public AgendamentoController(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }
    @GetMapping
    public String list() {
        return this.tarefasRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Tarefa::getData))
                .map(Tarefa::toString)
                .collect(Collectors.joining(",<br>"));
    }
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = null;
        var i = tarefa.getData();
        var l = tarefasRepository.findConflitos(i, i.plusMinutes(tarefa.getDuracao()));
        if (l.isEmpty()) {
            tarefa.setId(null);
            return ResponseEntity.ok(tarefasRepository.saveAndFlush(tarefa));
        }
        return ResponseEntity.ofNullable(novaTarefa);
    }
}
