package com.modong.backend.domain.evaluation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "평가 삭제 요청")
public class EvaluationDeleteRequest {

  @Schema(description = "평가 ID",  example = "1")
  @NotNull(message = "평가 id는 필수 항목입니다!")
  private Long evaluationId;

  @Builder
  public EvaluationDeleteRequest(Long evaluationId) {
    this.evaluationId = evaluationId;
  }


}