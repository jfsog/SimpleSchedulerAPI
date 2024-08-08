package org.jfsog.simpleschedulerapi.Controllers;

import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.jfsog.simpleschedulerapi.Service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class AgendamentoController {
    private final TarefaService tarefaService;
    public AgendamentoController(TarefaService  tarefaService) {
        this.tarefaService = tarefaService;
    }
    @GetMapping
    public String list() {
        return this.tarefaService.listar()
                                 .stream()
                                 .sorted(Comparator.comparing(Tarefa::getDataHoraInicio))
                                 .map(Tarefa::toString)
                                 .collect(Collectors.joining(",<br>"));
    }
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        var l = tarefaService.tarefasConflitantesCom(tarefa);
        if (l.isEmpty()) {
            tarefa.setId(null);
            return ResponseEntity.ok(tarefaService.salvar(tarefa));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    @GetMapping(value = "remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        this.tarefaService.deletar(id);
        return list();
    }
}
