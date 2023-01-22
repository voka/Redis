package com.modong.backend.unit.auth;
import static com.modong.backend.Fixtures.ACCESS_TOKEN;
import static com.modong.backend.Fixtures.REFRESH_TOKEN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.modong.backend.auth.AuthController;
import com.modong.backend.auth.Dto.TokenResponse;
import com.modong.backend.global.exception.auth.PasswordMismatchException;
import com.modong.backend.global.exception.member.MemberNotFoundException;
import com.modong.backend.unit.base.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(AuthController.class)
public class AuthControllerTest extends ControllerTest {

  private String requestBody;
  @BeforeEach
  public void init() throws JsonProcessingException {
    requestBody = objectMapper.writeValueAsString(loginRequest);
  }

  @DisplayName("로그인 - 유효한 정보의 로그인 요청이 오면 토큰을 발급하고 상태값 200을 반환해야 한다.")
  @WithMockUser
  @Test
  public void returnTokenWithStatusOKIfLoginRequestValid() throws Exception {
    // given
    given(authService.login(any()))
        .willReturn(new TokenResponse(ACCESS_TOKEN,REFRESH_TOKEN));

    // when
    ResultActions perform = mockMvc.perform(post("/api/v1/login")
        .contentType(MediaType.APPLICATION_JSON).with(csrf())
        .content(requestBody));

    // then
    perform
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("data.accessToken").value(ACCESS_TOKEN))
        .andExpect(jsonPath("data.refreshToken").value(REFRESH_TOKEN));
  }
  @DisplayName("로그인 - MemberId가 존재하지 않으면 상태값 404를 반환해야 한다.")
  @WithMockUser
  @Test
  public void throwNotFoundExceptionIfMemberIdNotExist() throws Exception{
    //given
    MemberNotFoundException expected = new MemberNotFoundException(loginRequest.getMemberId());
    given(authService.login(any()))
        .willThrow(expected);
    //when
    ResultActions perform = mockMvc.perform(post("/api/v1/login")
        .contentType(MediaType.APPLICATION_JSON).with(csrf())
        .content(requestBody));
    //then
    perform.andExpect(status().isNotFound());
  }
  @DisplayName("로그인 - 비밀번호가 틀리면 상태값 400를 반환해야 한다.")
  @WithMockUser
  @Test
  public void returnExceptionIfPasswordNotMatch() throws Exception{
    //given
    PasswordMismatchException expected = new PasswordMismatchException();
    given(authService.login(any()))
        .willThrow(expected);
    //when
    ResultActions perform = mockMvc.perform(post("/api/v1/login")
        .contentType(MediaType.APPLICATION_JSON).with(csrf())
        .content(requestBody));
    //then
    perform.andExpect(status().isBadRequest());
  }
}
