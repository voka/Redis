package com.modong.backend.Base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

  @GetMapping("/")
  public String running() {
    return "Server is running!!";
  }
}
