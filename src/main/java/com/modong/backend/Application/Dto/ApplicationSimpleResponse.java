package com.modong.backend.Application.Dto;

import com.modong.backend.Application.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ApplicationSimpleResponse {
  @Schema(description = "지원서 id", example = "1")
  private Long id;
  @Schema(description = "지원서 제목", example = "동아리 지원서 ver 1.0")
  private String title;

  public ApplicationSimpleResponse(Application application) {
    this.id = application.getId();
    this.title = application.getTitle();
  }
}
