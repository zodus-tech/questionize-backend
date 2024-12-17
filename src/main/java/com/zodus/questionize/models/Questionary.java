package com.zodus.questionize.models;

import com.zodus.questionize.infra.customuuid.CustomUUID;
import com.zodus.questionize.models.questions.Question;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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
  @CustomUUID
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer answersLimit;

  @OneToMany(mappedBy = "questionary", cascade = CascadeType.ALL)
  private List<Question> questions;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "imageId")
  private Image banner;

  @ManyToOne
  @JoinColumn(name = "departmentId")
  private Department department;

  @OneToMany(mappedBy = "questionary", cascade = CascadeType.ALL)
  private List<Submission> submissions;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "questionary_members_like",
      joinColumns = @JoinColumn(name = "questionaryId"),
      inverseJoinColumns = @JoinColumn(name = "memberId")
  )
  private List<Member> members;
}
