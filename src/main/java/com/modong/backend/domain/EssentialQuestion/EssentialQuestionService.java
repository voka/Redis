package com.modong.backend.domain.EssentialQuestion;

import static com.modong.backend.Enum.MessageCode.ERROR_REQ_PARAM_ID;

import com.modong.backend.domain.Application.Application;
import com.modong.backend.domain.ApplicationEssential.ApplicationEssentialService;
import com.modong.backend.domain.EssentialQuestion.Dto.EssentialQuestionRequest;
import com.modong.backend.domain.EssentialQuestion.Dto.EssentialQuestionResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EssentialQuestionService {

  private final EssentialQuestionRepository essentialQuestionRepository;
  private final ApplicationEssentialService applicationEssentialService;
  public EssentialQuestion findById(Long essentialQuestionId) {
    EssentialQuestion essentialQuestion = essentialQuestionRepository.findById(essentialQuestionId)
        .orElseThrow(() -> new IllegalArgumentException(ERROR_REQ_PARAM_ID.toString()));
    return essentialQuestion;
  }

  @Transactional
  public Long create(EssentialQuestionRequest essentialQuestionRequest, Application application) {
    EssentialQuestion essentialQuestion = new EssentialQuestion(essentialQuestionRequest);

    essentialQuestionRepository.save(essentialQuestion);

    applicationEssentialService.create(application, essentialQuestion);

    return essentialQuestion.getId();

  }

  public List<EssentialQuestionResponse> findAll() {

    List<EssentialQuestionResponse> essentialQuestions = essentialQuestionRepository.findAll().stream().map(EssentialQuestionResponse::new).collect(
        Collectors.toList());

    return essentialQuestions;

  }
}
