package com.modong.backend.Form.dto;

import com.modong.backend.Question.Dto.QuestionRequest;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import java.util.List;
import lombok.ToString;

@Getter
@ToString
public class FormRequest {

  @NotNull
  @ApiModelProperty(value = "지원서 ID", required = true, example = "1")
  private Long applicationId;

  @NotBlank
  @ApiModelProperty(value = "페이지 제목", required = true, example = "첫번째 페이지")
  private String title;

  @ApiModelProperty(value = "페이지에 들어갈 질문들", required = false)
  private List<QuestionRequest> questionRequests = new ArrayList<>();

}
