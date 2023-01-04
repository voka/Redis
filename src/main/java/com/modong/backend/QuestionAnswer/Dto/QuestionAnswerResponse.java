package com.modong.backend.QuestionAnswer.Dto;

import com.modong.backend.EssentialAnswer.EssentialAnswer;
import com.modong.backend.QuestionAnswer.QuestionAnswer;
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
