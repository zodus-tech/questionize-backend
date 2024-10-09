package com.zodus.questionize.dto;

public record AuthenticationDTO(
    AdministratorDTO user,
    String token
) {
}
