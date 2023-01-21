package com.modong.backend.auth.Dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberRegisterRequest {
  @NotNull

  private String memberId;
  @NotNull
  private String password;
  @NotNull
  private String name;
  @NotNull
  private String email;
  @NotNull
  private String phone;

  @NotNull
  private String clubCode;

  public MemberRegisterRequest(String memberId, String password, String name, String email,
      String phone, String clubCode) {
    this.memberId = memberId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.clubCode = clubCode;
  }
}
