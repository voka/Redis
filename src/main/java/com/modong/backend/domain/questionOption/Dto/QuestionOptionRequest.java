package com.modong.backend.domain.questionOption.Dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class QuestionOptionRequest {

  @ApiModelProperty(value = "선택하는 질문의 옵션 값들", required = false, example = "선택 질문의 값입니다.")
  @Nullable
  private List<String> values;

}
