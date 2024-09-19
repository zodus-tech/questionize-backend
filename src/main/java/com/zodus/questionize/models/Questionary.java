package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "questionary")
public class Questionary {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;
  private LocalDateTime createdAt;

  @OneToOne(mappedBy = "questionary")
  private QuestionaryOptions questionaryOptions;

  @OneToMany(mappedBy = "questionary")
  private Set<Question> questions;
}
