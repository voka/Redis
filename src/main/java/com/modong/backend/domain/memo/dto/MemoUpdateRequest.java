package com.modong.backend.domain.memo.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoUpdateRequest {
  @NotNull(message = "메모 id는 필수 항목입니다!")
  private Long memoId;
  @NotNull(message = "메모할 내용은 필수 항목입니다!")
  private String content;

  @Builder
  public MemoUpdateRequest(Long memoId, String content) {
    this.memoId = memoId;
    this.content = content;
  }

}
