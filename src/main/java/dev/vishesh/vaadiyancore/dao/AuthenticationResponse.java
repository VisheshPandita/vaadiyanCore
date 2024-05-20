package dev.vishesh.vaadiyancore.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.vishesh.vaadiyancore.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthenticationResponse
 * This class is used to store the authentication response details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
  private User user;
}
