package com.modong.backend.QuestionAnswer;

import com.modong.backend.Applicant.Applicant;
import com.modong.backend.Question.Question;
import com.modong.backend.Question.QuestionService;
import com.modong.backend.QuestionAnswer.Dto.QuestionAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionAnswerService {

  private final QuestionAnswerRepository questionAnswerRepository;
  private final QuestionService questionService;
  @Transactional
  public Long create(QuestionAnswerRequest questionAnswerRequest, Applicant applicant) {
    Question question = questionService.findById(questionAnswerRequest.getQuestionId());

    QuestionAnswer questionAnswer = new QuestionAnswer(questionAnswerRequest,question,applicant);

    questionAnswerRepository.save(questionAnswer);

    return questionAnswer.getId();

  }


}
