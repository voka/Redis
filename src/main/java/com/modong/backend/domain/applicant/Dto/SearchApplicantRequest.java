package com.modong.backend.domain.applicant.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@Schema(name = "지원자들 조회 요청")
public class SearchApplicantRequest{

  @NotNull
  @Schema(description = "지원자 상태", example = "ACCEPT(2),APPLICATION(3),INTERVIEW(4),SUCCESS(5)")
  private int applicantStatusCode;

  @Schema(description = "지원자 필터", example = "all/evaluating/fail")
  private String filter = "all";
}
