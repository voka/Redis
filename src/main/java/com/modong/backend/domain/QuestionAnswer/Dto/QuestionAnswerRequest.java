package com.modong.backend.domain.QuestionAnswer.Dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class QuestionAnswerRequest {

  @NotNull  // 질문 ID
  @Schema(description = "질문 ID", required = true, example = "1")
  private Long questionId;

  @Schema(description = "질문 답변", required = false, example = "일반 질문 답변입니다!")
  private String answer;

}
