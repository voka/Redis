package com.modong.backend.Enum;

import lombok.Getter;

@Getter
public enum MessageCode {
  //공통
  SUCCESS_GET("조회 성공", "SC001"), SUCCESS_GET_LIST("목록 조회 성공", "SC002"),
  SUCCESS_CREATE("저장 성공", "SC003"), SUCCESS_UPDATE("수정 성공", "SC004"),
  SUCCESS_DELETE("삭제 성공", "SC005"),

  ERROR_REQ_PARAM_ID("존재하지 않는 ID 입니다.", "EC001"),
  ERROR_SERVER("서버에서 오류가 발생했습니다.", "EC000");

  private String message;
  private String code;

  MessageCode(String message, String code) {
    this.message = message;
    this.code = code;
  }
}
