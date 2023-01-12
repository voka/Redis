package com.modong.backend.domain.questionAnswer.Dto;

import com.modong.backend.domain.questionAnswer.QuestionAnswer;
import lombok.Getter;

@Getter
public class QuestionAnswerResponse {
  private String essentialQuestion;
  private String essentialAnswer;

  public QuestionAnswerResponse(QuestionAnswer questionAnswer) {
    this.essentialQuestion = questionAnswer.getQuestion().getContent();
    this.essentialAnswer = questionAnswer.getAnswer();
  }
}
