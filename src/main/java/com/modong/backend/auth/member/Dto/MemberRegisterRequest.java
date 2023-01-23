package com.modong.backend.auth.member.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberRegisterRequest {
  @NotNull
  @Schema(description = "아이디", required = true, example = "test123")
  private String memberId;
  @NotNull
  @Schema(description = "비밀번호", required = true, example = "myPassword1234!")
  private String password;
  @NotNull
  @Schema(description = "이름", required = true, example = "tester")
  private String name;
  @NotNull
  @Schema(description = "이메일", required = true, example = "email@naver.com")
  private String email;
  @NotNull
  @Schema(description = "휴대폰 번호", required = true, example = "01032343243")
  private String phone;

  @NotNull
  @Schema(description = "동아리 코드", required = true, example = "F1KAO132KA")
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
