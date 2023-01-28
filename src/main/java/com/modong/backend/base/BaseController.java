package com.modong.backend.base;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "기본 API")
@RestController
public class BaseController {

  @GetMapping("/")
  @Operation(summary = "헬스체크용")
  public String running() {
    return "Server is running!!";
  }
}
