package com.modong.backend.domain.memo.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoDeleteRequest {

  @NotNull(message = "메모 id는 필수 항목입니다!")
  private Long memoId;

  @Builder
  public MemoDeleteRequest(Long memoId) {
    this.memoId = memoId;
  }


}