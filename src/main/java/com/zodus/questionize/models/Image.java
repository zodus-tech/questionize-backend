package com.zodus.questionize.models;

import com.zodus.questionize.infra.customuuid.CustomUUID;
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
  @CustomUUID
  private UUID id;

  private String name;
  private String type;

  @Lob
  @Column(columnDefinition = "MEDIUMBLOB")
  private String imageData;

  @OneToOne(mappedBy = "picture")
  private Member member;

  @OneToOne(mappedBy = "banner")
  private Questionary questionary;
}
