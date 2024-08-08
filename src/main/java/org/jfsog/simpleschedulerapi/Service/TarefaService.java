package org.jfsog.simpleschedulerapi.Service;

import org.jfsog.simpleschedulerapi.Domain.Tarefa;
import org.jfsog.simpleschedulerapi.Repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefasRepository tarefasRepository;
    public List<Tarefa> listar() {
        return tarefasRepository.findAll();
    }
    public Tarefa salvar(Tarefa tarefa) {
        return tarefasRepository.saveAndFlush(tarefa);
    }
    public void deletar(Long id) {
        tarefasRepository.deleteById(id);
    }
    public List<Tarefa> tarefasConflitantesCom(Tarefa tarefa) {
        return tarefasRepository.tarefasComConflitos(tarefa.getDataHoraInicio(), tarefa.getDataHoraFim());
    }
}
