package com.modong.backend.Applicant.Dto;

import com.modong.backend.Applicant.Applicant;
import com.modong.backend.EssentialAnswer.Dto.EssentialAnswerResponse;
import com.modong.backend.QuestionAnswer.Dto.QuestionAnswerResponse;
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
