package com.modong.backend.domain.memo.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoFindRequest {

  @NotNull(message = "지원자 id는 필수 항목입니다!")
  private Long applicantId;

  @NotNull(message = "지원서 id는 필수 항목입니다!")
  private Long applicationId;


  @Builder
  public MemoFindRequest(Long applicantId, Long applicationId) {
    this.applicantId = applicantId;
    this.applicationId = applicationId;
  }
}
