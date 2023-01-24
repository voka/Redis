package com.modong.backend.domain.club.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubRequest {

  @Schema(description = "동아리 이름", required = true, example = "testClub")
  private String name;
  @Schema(description = "동아리 프로필", required = true, example = "https://avatars.githubusercontent.com/u/38587274?v=4")
  private String profileImgUrl;

  public ClubRequest(String name, String profileImgUrl) {
    this.name = name;
    this.profileImgUrl = profileImgUrl;
  }
}
