package com.zodus.questionize.infra.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
  private String[] allowedOrigins;
}
