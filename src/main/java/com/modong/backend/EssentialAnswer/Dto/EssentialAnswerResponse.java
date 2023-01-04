package com.modong.backend.EssentialAnswer.Dto;

import com.modong.backend.EssentialAnswer.EssentialAnswer;
import lombok.Getter;

@Getter
public class EssentialAnswerResponse {

  private String essentialQuestion;
  private String essentialAnswer;

  public EssentialAnswerResponse(EssentialAnswer essentialAnswer) {
    this.essentialQuestion = essentialAnswer.getEssentialQuestion().getContent();
    this.essentialAnswer = essentialAnswer.getAnswer();
  }
}
