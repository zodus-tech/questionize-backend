package com.zodus.questionize.dto;

import java.util.UUID;

public record AnswerDTO(
    UUID id,
    String question,
    String answer
) {
}
