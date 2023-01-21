package com.modong.backend.auth.Dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {

  @NotNull
  private String memberId;
  @NotNull
  private String password;
  public LoginRequest(String memberId, String password) {
    this.memberId = memberId;
    this.password = password;
  }
}
