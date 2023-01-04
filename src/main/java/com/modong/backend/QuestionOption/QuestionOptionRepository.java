package com.modong.backend.QuestionOption;

import com.modong.backend.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface QuestionOptionRepository extends JpaRepository<QuestionOption,Long> {

  List<QuestionOption> findAllByQuestion(Question question);

}
