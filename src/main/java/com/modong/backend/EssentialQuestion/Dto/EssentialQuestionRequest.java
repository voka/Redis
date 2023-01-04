package com.modong.backend.EssentialQuestion.Dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EssentialQuestionRequest {

  private Long id;
  @NotNull
  private String content;
  private boolean isRequire;

}
