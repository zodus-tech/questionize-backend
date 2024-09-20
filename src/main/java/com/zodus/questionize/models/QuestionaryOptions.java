package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "questionary_options")
public class QuestionaryOptions {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer answersLimit;
  private Boolean anonymous;

  @OneToOne
  @JoinColumn(name = "questionaryId")
  private Questionary questionary;
}
