package com.modong.backend.Application.Dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import java.util.List;

@Getter
public class ApplicationRequest {

  @NotNull
  @Schema(description = "동아리 ID", required = true, example = "1")
  private Long clubId;

  @NotNull
  @Schema(description = "동아리 ID", required = true, example = "동아리 지원서 ver 1.0")
  private String title;

  @Schema(description = "필수질문 ID 리스트", required = false,  example = "[1,2,3]")
  private List<Long> essentialQuestionIds = new ArrayList<>();

}
