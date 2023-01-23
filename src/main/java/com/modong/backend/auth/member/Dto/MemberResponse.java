package com.modong.backend.auth.member.Dto;

import com.modong.backend.auth.member.Member;
import lombok.Getter;
@Getter
public class MemberResponse {

  private Long id;
  private String name;
  private String email;
  private String phone;

  public MemberResponse(Member member) {
    this.id = member.getId();
    this.name = member.getName();
    this.email = member.getEmail();
    this.phone = member.getPhone();
  }
}
