package com.modong.backend.EssentialAnswer;

import com.modong.backend.Applicant.Applicant;
import com.modong.backend.Application.Application;
import com.modong.backend.EssentialAnswer.Dto.EssentialAnswerRequest;
import com.modong.backend.EssentialQuestion.EssentialQuestion;
import com.modong.backend.EssentialQuestion.EssentialQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EssentialAnswerService {

  private final EssentialAnswerRepository essentialAnswerRepository;
  private final EssentialQuestionService essentialQuestionService;
  @Transactional
  public Long create(EssentialAnswerRequest essentialAnswerRequest, Applicant applicant) {
    EssentialQuestion essentialQuestion = essentialQuestionService.findById(essentialAnswerRequest.getEssentialQuestionId());

    EssentialAnswer essentialAnswer = new EssentialAnswer(essentialAnswerRequest,essentialQuestion,applicant);

    essentialAnswerRepository.save(essentialAnswer);

    return essentialAnswer.getId();
  }
}
