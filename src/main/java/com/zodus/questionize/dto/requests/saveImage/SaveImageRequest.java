package com.zodus.questionize.dto.requests.saveImage;

import java.util.Optional;
import java.util.UUID;

public record SaveImageRequest(
    Optional<String> name,
    Optional<UUID> memberId,
    Optional<UUID> questionaryId
) {
}
