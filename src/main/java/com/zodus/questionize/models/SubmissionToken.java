package com.zodus.questionize.models;

import com.zodus.questionize.infra.customuuid.CustomUUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
public class SubmissionToken {
  @Id
  @CustomUUID
  private UUID id;

  private LocalDateTime createdAt;
}
