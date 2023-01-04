package com.modong.backend.ClubAdmin;

import com.modong.backend.Base.BaseTimeEntity;
import com.modong.backend.Club.Club;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubAdmin extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String adminId;

  private String password;

  private String nickName;

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

}
