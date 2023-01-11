package com.modong.backend.domain.Applicant.Dto;

import com.modong.backend.domain.Applicant.Applicant;
import com.modong.backend.domain.EssentialAnswer.Dto.EssentialAnswerResponse;
import com.modong.backend.domain.QuestionAnswer.Dto.QuestionAnswerResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.Getter;
import java.util.List;

@Getter
public class ApplicantDetailResponse extends ApplicantSimpleResponse {
  private List<EssentialAnswerResponse> essentialAnswers = new ArrayList<>();
  private List<QuestionAnswerResponse> questionAnswers = new ArrayList<>();

  public ApplicantDetailResponse(Applicant applicant) {
    super(applicant);
    this.essentialAnswers = applicant.getEssentialAnswers().stream().map(EssentialAnswerResponse::new).collect(
        Collectors.toList());
    this.questionAnswers = applicant.getQuestionAnswers().stream().map(QuestionAnswerResponse::new).collect(
        Collectors.toList());
  }
}
