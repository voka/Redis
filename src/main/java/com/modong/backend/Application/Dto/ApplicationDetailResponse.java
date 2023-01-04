package com.modong.backend.Application.Dto;

import com.modong.backend.Application.Application;
import com.modong.backend.EssentialQuestion.Dto.EssentialQuestionResponse;
import com.modong.backend.Form.dto.FormResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ApplicationDetailResponse extends ApplicationSimpleResponse{

  private List<EssentialQuestionResponse> essentialQuestions = new ArrayList<>();
  private List<FormResponse> forms = new ArrayList<>();

  public ApplicationDetailResponse(Application application) {
    super(application);
  }
  public void addEssentialQuestion(EssentialQuestionResponse essentialQuestionResponse){
    this.essentialQuestions.add(essentialQuestionResponse);
  }
  public void addForm(FormResponse formResponse){
    this.forms.add(formResponse);
  }


}
