package com.modong.backend.domain.club;

import com.modong.backend.base.BaseTimeEntity;
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
  private  String introduce;

  private String profileImgUrl;

  public Club(String name, String introduce, String profileUrl) {
    this.name = name;
    this.introduce = introduce;
    this.profileImgUrl = profileUrl;
  }
}