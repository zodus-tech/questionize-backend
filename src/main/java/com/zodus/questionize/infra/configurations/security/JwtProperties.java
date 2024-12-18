package com.zodus.questionize.infra.configurations.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
  private String secret;
  private long expirationTimeInHours;
  private long expirationTimeInDays;
}