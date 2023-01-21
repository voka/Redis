package com.modong.backend.domain.club.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubRequest {

  private String name;
  private String profileImgUrl;

  public ClubRequest(String name, String profileImgUrl) {
    this.name = name;
    this.profileImgUrl = profileImgUrl;
  }
}
