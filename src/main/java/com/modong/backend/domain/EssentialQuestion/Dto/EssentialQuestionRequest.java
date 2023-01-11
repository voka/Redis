package com.modong.backend.domain.EssentialQuestion.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EssentialQuestionRequest {

  @NotNull
  private Long id;
  @NotBlank
  private String content;
  private boolean isRequire;

}
