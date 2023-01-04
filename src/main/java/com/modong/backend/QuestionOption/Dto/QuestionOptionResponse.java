package com.modong.backend.QuestionOption.Dto;

import com.modong.backend.QuestionOption.QuestionOption;
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
