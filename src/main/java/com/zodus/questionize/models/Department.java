package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true)
  private String name;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private Set<Administrator> administrators;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private Set<Questionary> questionnaires;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private Set<Member> members;
}
