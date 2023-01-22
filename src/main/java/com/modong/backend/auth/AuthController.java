package com.modong.backend.auth;

import com.modong.backend.Enum.CustomCode;
import com.modong.backend.auth.Dto.LoginRequest;
import com.modong.backend.auth.Dto.TokenResponse;
import com.modong.backend.base.Dto.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags =  "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  @Operation(summary = "로그인", description = "아이디와 비밀번호를 사용해 로그인을 한다.")
  public ResponseEntity login(@Validated @RequestBody LoginRequest loginRequest){
    TokenResponse tokens = authService.login(loginRequest);
    return ResponseEntity.ok(new BaseResponse(tokens, HttpStatus.OK.value(), CustomCode.SUCCESS_LOGIN));
  }


}
