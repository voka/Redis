package com.modong.backend.QuestionOption;

import com.modong.backend.QuestionOption.Dto.QuestionOptionRequest;
import com.modong.backend.QuestionOption.Dto.QuestionOptionResponse;
import com.modong.backend.Question.Question;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionOptionService {

  private final QuestionOptionRepository questionOptionRepository;

  public QuestionOption create(QuestionOptionRequest questionOptionRequest, Question question) {
    QuestionOption questionOption = new QuestionOption(questionOptionRequest,question);

    questionOptionRepository.save(questionOption);

    return questionOption;
  }

  public List<QuestionOptionResponse> findAllByQuestion(Question question) {

    List<QuestionOptionResponse> options = questionOptionRepository.findAllByQuestion(question).stream().map(
        QuestionOptionResponse::new).collect(
        Collectors.toList());
    return options;

  }
}
