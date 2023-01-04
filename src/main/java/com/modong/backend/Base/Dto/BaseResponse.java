package com.modong.backend.Base.Dto;

import com.modong.backend.Enum.MessageCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "기본 반환 형식")
public class BaseResponse {
  @Schema(description = "상태값", example = "200")
  private int status;
  @Schema(description = "응답 메시지", example = "조회 성공")
  private String message;
  @Schema(description = "반환 코드 값", example = "SC001")
  private String code;
  @Schema(description = "반환 데이터")
  private Object data;

  public BaseResponse(int status, MessageCode messageCode) {
    this.status = status;
    this.message = messageCode.getMessage();
    this.code = messageCode.getCode();
  }

  public BaseResponse(Object body, int status, MessageCode messageCode) {
    this.data = body;
    this.status = status;
    this.message = messageCode.getMessage();
    this.code = messageCode.getCode();
  }
}
