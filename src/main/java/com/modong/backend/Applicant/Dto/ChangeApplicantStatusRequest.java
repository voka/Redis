package com.modong.backend.Applicant.Dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ChangeApplicantStatusRequest {

  @NotNull
  private String applicationStatus;
}
