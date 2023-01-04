package com.modong.backend.Form;

import static com.modong.backend.Base.MessageCode.ERROR_REQ_PARAM_ID;

import com.modong.backend.Application.Application;
import com.modong.backend.Application.ApplicationService;
import com.modong.backend.Form.dto.FormRequest;
import com.modong.backend.Form.dto.FormResponse;
import com.modong.backend.Question.Dto.QuestionRequest;
import com.modong.backend.Question.Dto.QuestionResponse;
import com.modong.backend.Question.Question;
import com.modong.backend.Question.QuestionService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FormService {

  private final FormRepository formRepository;
  private final QuestionService questionService;
  private final ApplicationService applicationService;

  @Transactional
  public Long create(FormRequest formRequest) {

    Application application = applicationService.findSimpleById(formRequest.getApplicationId());

    Form form = new Form(formRequest,application);

    form.getQuestions().clear();

    for(QuestionRequest questionRequest : formRequest.getQuestionRequests()){
      Question question = questionService.create(questionRequest,form);
      form.addQuestion(question);
    }

    formRepository.save(form);

    return form.getId();
  }

  public FormResponse findById(Long formId) {

    Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException(ERROR_REQ_PARAM_ID.toString()));

    return new FormResponse(form);
  }

  public List<FormResponse> findAllByApplicationId(Long applicationId) {

    Application application = applicationService.findSimpleById(applicationId);

    List<Form> forms = formRepository.findAllByApplication(application);

    List<FormResponse> results = new ArrayList<>();

    for(Form form : forms){
      results.add(new FormResponse(form));
    }

    return results;
  }

  @Transactional
  public Long update(Long formId, FormRequest formRequest) {

    Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException(ERROR_REQ_PARAM_ID.toString()));

    form.updateForm(formRequest);
    //기존 질문들 삭제
    form.getQuestions().removeAll(form.getQuestions());

    for(QuestionRequest questionRequest : formRequest.getQuestionRequests()){
      Question question = questionService.create(questionRequest,form);
      form.addQuestion(question);
    }

    formRepository.save(form);

    return form.getId();

  }

  @Transactional
  public void deleteForm(Long formId) {
    Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException(ERROR_REQ_PARAM_ID.toString()));
    formRepository.delete(form);
  }
}