package com.modong.backend.QuestionOption.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class QuestionOptionRequest {

  @ApiModelProperty(value = "선택하는 질문의 옵션 값", required = false, example = "선택 질문의 값입니다.")
  private String value;

}
