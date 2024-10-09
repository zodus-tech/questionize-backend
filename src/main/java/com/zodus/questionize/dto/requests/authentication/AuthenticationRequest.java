package com.zodus.questionize.dto.requests.authentication;

public record AuthenticationRequest(
    String username,
    String password,
    boolean rememberUser
) {
}
