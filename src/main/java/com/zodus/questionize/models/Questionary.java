package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "questionary")
public class Questionary {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @OneToOne(mappedBy = "questionary", cascade = CascadeType.ALL)
  private QuestionaryOptions questionaryOptions;

  @OneToMany(mappedBy = "questionary", cascade = CascadeType.ALL)
  private Set<Question> questions;
}
