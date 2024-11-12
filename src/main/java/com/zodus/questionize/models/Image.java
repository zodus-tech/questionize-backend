package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private String type;

  @Lob
  @Column(columnDefinition = "MEDIUMBLOB")
  private String imageData;

  @OneToOne(mappedBy = "picture", cascade = CascadeType.ALL)
  private Member member;

  @OneToOne(mappedBy = "banner", cascade = CascadeType.ALL)
  private Questionary questionary;
}
