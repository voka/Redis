package com.modong.backend.EssentialQuestion.Dto;

import com.modong.backend.EssentialQuestion.EssentialQuestion;
import lombok.Getter;

@Getter
public class EssentialQuestionResponse {

  private Long id;
  private String content;
  private boolean isRequire;


  public EssentialQuestionResponse(EssentialQuestion essentialQuestion) {
    this.id = essentialQuestion.getId();
    this.content = essentialQuestion.getContent();
    this.isRequire = essentialQuestion.isRequire();
  }
}
