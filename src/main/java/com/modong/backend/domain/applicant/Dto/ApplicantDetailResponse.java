package com.modong.backend.domain.applicant.Dto;

import com.modong.backend.domain.applicant.Applicant;
import com.modong.backend.domain.essentialAnswer.Dto.EssentialAnswerResponse;
import com.modong.backend.domain.questionAnswer.Dto.QuestionAnswerResponse;
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
