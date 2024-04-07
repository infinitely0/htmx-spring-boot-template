package com.example.todo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@SqlGroup({
  @Sql(scripts = "classpath:add_todos.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
})
class ToDoControllerTests {

  @Autowired private MockMvc mvc;

  @Test
  @SneakyThrows
  void createToDo() {
    mvc.perform(
            post("/todos")
                .content("todo")
                .param("title", "Write tests")
                .param("description", "Write tests for the to-do app"))
        .andExpect(status().isOk());
  }

  @Test
  @SneakyThrows
  void readToDo() {
    mvc.perform(get("/todos/1"))
        .andExpect(status().isOk())
        .andExpect(
            result -> {
              String content = result.getResponse().getContentAsString();
              assertTrue(content.contains("Learn SQL"));
            });
  }
}
