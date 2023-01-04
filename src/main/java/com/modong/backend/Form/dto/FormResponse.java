package com.modong.backend.Form.dto;

import com.modong.backend.Form.Form;
import com.modong.backend.Question.Dto.QuestionResponse;
import com.modong.backend.Question.Question;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.Getter;
import java.util.List;

@Getter
public class FormResponse {

  private Long id;
  private String title;
  private List<QuestionResponse> questions = new ArrayList<>();

  public FormResponse(Form form) {
    this.id = form.getId();
    this.title = form.getTitle();
    this.questions = form.getQuestions().stream().map(QuestionResponse::new).collect(Collectors.toList());
  }
  
}