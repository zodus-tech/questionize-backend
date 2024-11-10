package com.zodus.questionize.dto.requests.questionary.submission;

import java.util.UUID;

public record SubmitAnswerRequest(
    UUID questionId,
    String answer
) {
}
