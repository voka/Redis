package com.modong.backend.domain.Club.ClubManager;

import com.modong.backend.Base.BaseTimeEntity;
import com.modong.backend.domain.Club.Club;
import com.modong.backend.domain.Judge.Judge;
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
public class ClubManager extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String managerId;

  private String password;

  private String nickName;

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

  @OneToMany(mappedBy = "clubManager")
  private List<Judge> judges = new ArrayList<>();
}
