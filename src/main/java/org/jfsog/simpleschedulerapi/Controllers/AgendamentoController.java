package org.jfsog.simpleschedulerapi.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.jfsog.simpleschedulerapi.Service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AgendamentoController {
    private final TarefaService tarefaService;
    private final ObjectMapper objectMapper;
    @GetMapping
    public String list() {
        var collect = this.tarefaService.listar()
                                        .stream()
                                        .sorted(Comparator.comparing(Tarefa::getDataHoraInicio))
                                        .map(Tarefa::toString)
                                        .toList();
        try {
            return objectMapper.writeValueAsString(collect);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
