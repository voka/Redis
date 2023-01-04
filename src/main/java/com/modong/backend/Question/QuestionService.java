package com.modong.backend.Question;

import static com.modong.backend.Base.MessageCode.ERROR_REQ_PARAM_ID;

import com.modong.backend.Enum.QuestionType;
import com.modong.backend.Form.Form;
import com.modong.backend.QuestionOption.Dto.QuestionOptionRequest;
import com.modong.backend.QuestionOption.Dto.QuestionOptionResponse;
import com.modong.backend.QuestionOption.QuestionOption;
import com.modong.backend.QuestionOption.QuestionOptionService;
import com.modong.backend.Question.Dto.QuestionRequest;
import com.modong.backend.Question.Dto.QuestionResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

  private final QuestionRepository questionRepository;

  private final QuestionOptionService questionOptionService;

  public Question findById(Long questionId) {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new IllegalArgumentException(ERROR_REQ_PARAM_ID.toString()));
    return question;
  }

  // 일단 중복검사 없이 로직 작성 추후에 추가할 수도 있을듯
  public Question create(QuestionRequest questionRequest, Form form) {

    Question question = new Question(questionRequest,form);
    question.getQuestionOptions().clear();

    for(QuestionOptionRequest questionOptionRequest : questionRequest.getQuestionOptionRequests()){
      QuestionOption questionOption = new QuestionOption(questionOptionRequest,question);
      question.addOption(questionOption);
    }

    questionRepository.save(question);

    return question;
  }

  public List<QuestionResponse> findAllByForm(Form form) {

    List<Question> questions = questionRepository.findAllByForm(form);

    List<QuestionResponse> results = new ArrayList<>();

    for(Question question : questions){
      List<QuestionOptionResponse> options = new ArrayList<>();
      if(question.getQuestionType() == QuestionType.CHECKBOX_QUESTION ||
          question.getQuestionType() == QuestionType.RADIO_QUESTION){
        options = questionOptionService.findAllByQuestion(question);
      }
      results.add(new QuestionResponse(question));
    }

    return results;
  }

}
