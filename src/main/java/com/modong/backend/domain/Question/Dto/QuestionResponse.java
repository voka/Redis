package com.modong.backend.domain.Question.Dto;

import com.modong.backend.domain.Question.Question;
import com.modong.backend.domain.QuestionOption.QuestionOption;
import java.util.ArrayList;
import lombok.Getter;
import java.util.List;

@Getter
public class QuestionResponse {

  private Long id;
  private String content;

  private int questionType;

  private List<String> options = new ArrayList<>();

  public QuestionResponse(Question question) {
    this.id = question.getId();
    this.content = question.getContent();
    this.questionType = question.getQuestionType().getCode();
    for(QuestionOption questionOption : question.getQuestionOptions()){
      options.add(questionOption.getValue());
    }
//    this.options = question.getQuestionOptions().stream().map(QuestionOptionResponse::new).collect(
//        Collectors.toList()).toString();
  }
}
