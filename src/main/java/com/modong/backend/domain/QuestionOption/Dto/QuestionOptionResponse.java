package com.modong.backend.domain.QuestionOption.Dto;

import com.modong.backend.domain.QuestionOption.QuestionOption;
import lombok.Getter;

@Getter
public class QuestionOptionResponse {

  private Long id;
  private String value;

  public QuestionOptionResponse(QuestionOption questionOption) {
    this.id = questionOption.getId();
    this.value = questionOption.getValue();
  }
}
