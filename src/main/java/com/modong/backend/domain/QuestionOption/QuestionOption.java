package com.modong.backend.domain.QuestionOption;

import com.modong.backend.Base.BaseTimeEntity;
import com.modong.backend.domain.Question.Question;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionOption extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Lob
  private String value;

  @ManyToOne(fetch = FetchType.LAZY)
  private Question question;

  public QuestionOption(String value, Question question) {
    this.value = value;
    this.question = question;
  }
}