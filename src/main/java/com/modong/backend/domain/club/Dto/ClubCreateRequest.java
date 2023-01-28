package com.modong.backend.domain.club.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "동아리 생성 요청")
public class ClubCreateRequest {

  @Schema(description = "동아리 이름", example = "testClub")
  private String name;
  @Schema(description = "동아리 프로필", example = "https://avatars.githubusercontent.com/u/38587274?v=4")
  private String profileImgUrl;

  public ClubCreateRequest(String name, String profileImgUrl) {
    this.name = name;
    this.profileImgUrl = profileImgUrl;
  }
}
