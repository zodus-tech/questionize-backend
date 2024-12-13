package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
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
  private List<Administrator> administrators = new ArrayList<>();

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<Questionary> questionnaires = new ArrayList<>();

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<Member> members = new ArrayList<>();

  public Department(String name) {
    this.name = name;
  }
}
