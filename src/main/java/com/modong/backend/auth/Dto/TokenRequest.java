package com.modong.backend.auth.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenRequest {

  private Long memberId;
  private String refreshToken;

  public TokenRequest(Long memberId, String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
  }
}
