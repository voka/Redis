package com.modong.backend.Applicant.Dto;

import com.modong.backend.EssentialAnswer.Dto.EssentialAnswerRequest;
import com.modong.backend.QuestionAnswer.Dto.QuestionAnswerRequest;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ApplicantRequest {

  @NotNull
  @Schema(description = "지원서 ID", required = true, example = "1")
  private Long applicationId;

  @NotBlank
  @Schema(description = "지원자 이름", required = true, example = "홍길동")
  private String name;

  @NotEmpty(message = "Input essential Answer list cannot be empty.")
  @Schema(description = "필수 질문 답변들", required = false)
  private List<EssentialAnswerRequest> essentialAnswers = new ArrayList<>();

  @Schema(description = "질문 답변들", required = false)
  private List<QuestionAnswerRequest> questionAnswers = new ArrayList<>();

}
