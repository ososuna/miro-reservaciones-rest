package dev.ososuna.miro.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="miro.rest")
public class PropertiesConfig {
  private String jwtSecret;
  private int jwtAccessExpirationMs;
}
