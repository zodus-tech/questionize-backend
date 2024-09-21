package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
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
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @OneToOne
  @JoinColumn(name = "imageId")
  private Image picture;

  @OneToMany(mappedBy = "member")
  private Set<Question> questions;
}
