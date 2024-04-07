package com.example.todo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "todo")
public class ToDo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "A title is required")
  @Length(max = 100, message = "Title must be under 100 characters")
  private String title;

  @Length(max = 250, message = "Description must be under than 250 characters")
  private String description;

  private Boolean completed;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  public OffsetDateTime createdAt;
}
