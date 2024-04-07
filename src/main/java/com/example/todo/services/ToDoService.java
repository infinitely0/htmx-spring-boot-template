package com.example.todo.services;

import com.example.todo.models.ToDo;
import com.example.todo.repositories.ToDoRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoService {

  private final ToDoRepository toDoRepository;

  public List<ToDo> list() {
    return toDoRepository.findAll().stream()
        .sorted(Comparator.comparing(ToDo::getCreatedAt))
        .toList();
  }

  public ToDo create(ToDo toDo) {
    toDo.setCompleted(false);
    return toDoRepository.save(toDo);
  }

  public Optional<ToDo> read(Long id) {
    return toDoRepository.findById(id);
  }

  public ToDo update(Long id, ToDo toDo) {
    return toDoRepository
        .findById(id)
        .map(
            existing -> {
              if (toDo.getTitle() != null) {
                existing.setTitle(toDo.getTitle());
              }
              if (toDo.getDescription() != null) {
                existing.setDescription(toDo.getDescription());
              }
              if (toDo.getCompleted() != null) {
                existing.setCompleted(toDo.getCompleted());
              }
              return existing;
            })
        .map(toDoRepository::save)
        .orElseThrow();
  }

  public void delete(Long id) {
    toDoRepository.deleteById(id);
  }
}
