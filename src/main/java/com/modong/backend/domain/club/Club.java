package com.modong.backend.domain.club;

import com.modong.backend.base.BaseTimeEntity;
import com.modong.backend.domain.club.Dto.ClubRequest;
import com.modong.backend.utils.UUIDGenerateUtils;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String profileImgUrl;

  private String clubCode = UUIDGenerateUtils.makeShortUUID(); // 10자리 유니크 아이디 부여
  public Club(ClubRequest clubRequest) {
    this.name = clubRequest.getName();
    this.profileImgUrl = clubRequest.getProfileImgUrl();
  }

  public Club(Long id, ClubRequest clubRequest) {
    this.id = id;
    this.name = clubRequest.getName();
    this.profileImgUrl = clubRequest.getProfileImgUrl();
  }
}