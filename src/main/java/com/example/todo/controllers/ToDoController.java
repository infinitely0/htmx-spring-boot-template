package com.example.todo.controllers;

import com.example.todo.models.ToDo;
import com.example.todo.services.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ToDoController {

  private final ToDoService toDoService;

  @GetMapping("/todos")
  public ModelAndView list() {
    ModelAndView mv = new ModelAndView("todos/list");
    mv.addObject("toDos", toDoService.list());
    return mv;
  }

  @GetMapping("/todos/new")
  public String init() {
    return "todos/new";
  }

  @GetMapping("/todos/{id}/edit")
  public ModelAndView edit(@PathVariable Long id) {
    ToDo toDo = toDoService.read(id).orElseThrow();
    ModelAndView mv = new ModelAndView("todos/edit");
    mv.addObject("toDo", toDo);
    return mv;
  }

  @PostMapping("/todos")
  public ModelAndView create(@ModelAttribute ToDo toDo) {
    log.info("Creating to-do: {}", toDo);
    ToDo saved = toDoService.create(toDo);
    ModelAndView mv = new ModelAndView("todos/show");
    mv.addObject("toDo", saved);
    return mv;
  }

  @GetMapping("/todos/{id}")
  public ModelAndView read(@PathVariable Long id) {
    ToDo toDo = toDoService.read(id).orElseThrow();
    ModelAndView mv = new ModelAndView("todos/show");
    mv.addObject("toDo", toDo);
    return mv;
  }

  @PatchMapping("/todos/{id}")
  public ModelAndView update(@PathVariable Long id, @ModelAttribute ToDo toDo) {
    log.info("Updating to-do {}: {}", id, toDo);
    ModelAndView mv = new ModelAndView("todos/show");
    ToDo updated = toDoService.update(id, toDo);
    mv.addObject("toDo", updated);
    return mv;
  }

  @DeleteMapping("/todos/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    log.info("Deleting to-do {}", id);
    toDoService.read(id).ifPresent(toDo -> toDoService.delete(id));
    return ResponseEntity.ok().build();
  }
}
