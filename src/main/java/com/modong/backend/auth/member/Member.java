package com.modong.backend.auth.member;

import com.modong.backend.Enum.ProviderName;
import com.modong.backend.auth.memberRole.MemberRole;
import com.modong.backend.base.BaseTimeEntity;
import com.modong.backend.domain.club.clubMemeber.ClubMember;
import com.modong.backend.domain.judge.Judge;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String memberId;
  private String password;
  private String name;
  private String nickName;
  private String email;
  private String phone;
  @Enumerated(EnumType.STRING)
  private ProviderName providerName;

  @OneToMany(mappedBy = "member")
  private List<ClubMember> clubs = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Judge> judges = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<MemberRole> roles = new ArrayList<>();
}
