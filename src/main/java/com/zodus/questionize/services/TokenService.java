package com.zodus.questionize.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zodus.questionize.infra.configurations.security.JwtProperties;
import com.zodus.questionize.models.Administrator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class TokenService {
  private final AdministratorService administratorService;
  private final String AMERICA_SAO_PAULO_OFFSET = "-03:00";
  private final JwtProperties jwtProperties;
  private final static String issuer = "Questionize";

  public String generateToken(UserDetails userDetails, boolean rememberUser) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
      return JWT.create()
          .withIssuer(issuer)
          .withSubject(userDetails.getUsername())
          .withExpiresAt(generateExpirationDate(rememberUser))
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }

  public boolean isTokenNotExpired(String token) {
    DecodedJWT decodedJWT = JWT.decode(token);
    Instant expireDate = decodedJWT.getExpiresAtAsInstant();
    return LocalDateTime.now().toInstant(ZoneOffset.of(AMERICA_SAO_PAULO_OFFSET)).isBefore(expireDate);
  }

  public Administrator extractUser(String bearerToken) {
    String token = extractJWTToken(bearerToken);
    String subject = extractSubject(token);
    return administratorService.findByUsername(subject);
  }

  public String extractSubject(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
      return JWT.require(algorithm)
          .withIssuer(issuer)
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException exception) {
      return "";
    }
  }

  private String extractJWTToken(String bearerToken) {
    return bearerToken.replace("Bearer ", "");
  }

  protected Instant generateExpirationDate(boolean rememberUser) {
    return rememberUser ?
        LocalDateTime.now().plusDays(jwtProperties.getExpirationTimeInDays())
            .toInstant(ZoneOffset.of(AMERICA_SAO_PAULO_OFFSET)) :
        LocalDateTime.now().plusHours(jwtProperties.getExpirationTimeInHours())
            .toInstant(ZoneOffset.of(AMERICA_SAO_PAULO_OFFSET));
  }
}
