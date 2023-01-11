package com.modong.backend.domain.ApplicationEssential;

import com.modong.backend.domain.Application.Application;
import com.modong.backend.domain.EssentialQuestion.EssentialQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationEssentialService {

  private final ApplicationEssentialRepository applicationEssentialRepository;


  @Transactional
  public Long create(Application application, EssentialQuestion essentialQuestion) {
    ApplicationEssential applicationEssential = new ApplicationEssential(application,essentialQuestion);

    applicationEssentialRepository.save(applicationEssential);

    return applicationEssential.getId();
  }

  @Transactional
  public void deleteAll(List<ApplicationEssential> applicationEssentials) {
    applicationEssentialRepository.deleteAll(applicationEssentials);
  }
}
