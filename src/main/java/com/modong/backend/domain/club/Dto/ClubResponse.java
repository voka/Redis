package com.modong.backend.domain.club.Dto;
import com.modong.backend.domain.club.Club;

public class ClubResponse {
  private Long id;
  private String name;
  private String profileImgUrl;
  private String clubCode;
  public ClubResponse(Club club) {
    this.id = club.getId();
    this.name = club.getName();
    this.profileImgUrl = club.getProfileImgUrl();
    this.clubCode = club.getClubCode();
  }
}
