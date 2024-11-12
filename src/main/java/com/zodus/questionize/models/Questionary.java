package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "questionary")
@AllArgsConstructor
@NoArgsConstructor
public class Questionary {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer answersLimit;

  @OneToMany(mappedBy = "questionary", cascade = CascadeType.ALL)
  private Set<Question> questions;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "imageId")
  private Image banner;

  @ManyToOne
  @JoinColumn(name = "departmentId")
  private Department department;

  @OneToMany(mappedBy = "questionary", cascade = CascadeType.ALL)
  private Set<Submission> submissions;
}
