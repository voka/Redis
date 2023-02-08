package com.modong.backend.domain.memo.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoCreateRequest {

  @NotNull(message = "지원서 id는 필수 항목입니다!")
  private Long applicationId;
  @NotNull(message = "지원자 id는 필수 항목입니다!")
  private Long applicantId;
  @NotNull(message = "메모할 내용은 필수 항목입니다!")
  private String content;

  @Builder
  public MemoCreateRequest(Long applicationId, Long applicantId, String content) {
    this.applicationId = applicationId;
    this.applicantId = applicantId;
    this.content = content;
  }

}
