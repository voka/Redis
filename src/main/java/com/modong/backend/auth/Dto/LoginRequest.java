package com.modong.backend.auth.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
  @NotNull
  @Schema(description = "아이디", required = true, example = "test123")
  private String memberId;
  @NotNull
  @Schema(description = "아이디", required = true, example = "myPassword1234!")
  private String password;
  public LoginRequest(String memberId, String password) {
    this.memberId = memberId;
    this.password = password;
  }
}
