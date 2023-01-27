package com.modong.backend.domain.club.Dto;

import com.modong.backend.domain.club.Club;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ClubCreateResponse {

  private Long id;
  private String code;

  public ClubCreateResponse(Club club) {
    this.id = club.getId();
    this.code = club.getClubCode();
  }
}
