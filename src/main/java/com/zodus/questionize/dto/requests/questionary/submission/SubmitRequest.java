package com.zodus.questionize.dto.requests.questionary.submission;

import java.util.List;
import java.util.UUID;

public record SubmitRequest(
    List<SubmitAnswerRequest> answers,
    UUID memberId
) {
}
