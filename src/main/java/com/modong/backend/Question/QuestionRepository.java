package com.modong.backend.Question;

import com.modong.backend.Form.Form;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

  List<Question> findAllByFormId(Long formId);
}
