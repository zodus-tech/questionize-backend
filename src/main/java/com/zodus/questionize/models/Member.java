package com.zodus.questionize.models;

import com.zodus.questionize.infra.customuuid.CustomUUID;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
  @Id
  @CustomUUID
  private UUID id;

  @Column(nullable = false)
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "imageId")
  private Image picture;

  @ManyToOne
  @JoinColumn(name = "departmentId")
  private Department department;

  @ManyToMany(mappedBy = "members")
  private List<Questionary> questionnaires;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Submission> submissions;
}
