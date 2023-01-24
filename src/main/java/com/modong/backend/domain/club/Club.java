package com.modong.backend.domain.club;

import com.modong.backend.base.BaseTimeEntity;
import com.modong.backend.domain.club.Dto.ClubRequest;
import com.modong.backend.domain.club.clubMemeber.ClubMember;
import com.modong.backend.utils.UUIDGenerateUtils;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

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
  @OneToMany(mappedBy = "club")
  private List<ClubMember> members = new ArrayList<>();
  public Club(ClubRequest clubRequest) {
    this.name = clubRequest.getName();
    this.profileImgUrl = clubRequest.getProfileImgUrl();
  }

  public Club(Long id, ClubRequest clubRequest) {
    this.id = id;
    this.name = clubRequest.getName();
    this.profileImgUrl = clubRequest.getProfileImgUrl();
  }
  public void addMember(ClubMember clubMember){
    this.members.add(clubMember);
  }
}