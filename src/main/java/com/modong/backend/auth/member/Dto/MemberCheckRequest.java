package com.modong.backend.auth.member.Dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberCheckRequest {

  private String memberId;


  public MemberCheckRequest(String memberId) {
    this.memberId = memberId;
  }
}
