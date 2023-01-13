package com.modong.backend.domain.essentialQuestion.Dto;

import com.modong.backend.domain.essentialQuestion.EssentialQuestion;
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
