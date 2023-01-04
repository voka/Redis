package com.modong.backend.Question.Dto;

import com.modong.backend.QuestionOption.Dto.QuestionOptionRequest;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import lombok.Getter;
import java.util.List;

@Getter
public class QuestionRequest {

  @NotNull
  @ApiModelProperty(value = "질문 유형", required = true, example = "1", allowableValues = "1,2,3")
  private int questionType;

  @NotNull
  @ApiModelProperty(value = "질문 내용", required = true, example = "동아리에 들어와서 어떤걸 경험하고 싶나요?")
  private String content;
  @ApiModelProperty(value = "질문 옵션들", required = false)
  List<QuestionOptionRequest> questionOptionRequests = new ArrayList<>();

}
