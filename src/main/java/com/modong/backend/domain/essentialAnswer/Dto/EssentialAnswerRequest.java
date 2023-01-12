package com.modong.backend.domain.essentialAnswer.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EssentialAnswerRequest {


  @NotNull // 필수 질문 ID
  @Schema(description = "필수 질문 ID", required = true, example = "1")
  private Long essentialQuestionId;

  //필수 질문 답변
  @NotNull
  @Schema(description = "필수 질문 답변", required = true, example = "필수 질문 답변입니다!")
  private String answer;

}
