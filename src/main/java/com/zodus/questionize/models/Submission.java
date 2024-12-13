package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "submission")
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private LocalDateTime submittedAt;

  @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
  private List<Answer> answers;

  @ManyToOne
  @JoinColumn(name = "questionaryId", nullable = false)
  private Questionary questionary;
}
