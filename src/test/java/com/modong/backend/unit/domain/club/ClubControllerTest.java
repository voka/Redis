package com.modong.backend.unit.domain.club;

import static com.modong.backend.Fixtures.AuthFixture.ACCESS_TOKEN;
import static com.modong.backend.Fixtures.AuthFixture.REFRESH_TOKEN;
import static com.modong.backend.Fixtures.ClubFixture.CLUB_ID;
import static com.modong.backend.Fixtures.ClubFixture.CLUB_NAME;
import static com.modong.backend.Fixtures.ClubFixture.CLUB_PROFILE_IMG_URL;
import static com.modong.backend.Fixtures.MemberFixture.MEMBER_ID;
import static com.modong.backend.Fixtures.MemberFixture.PASSWORD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.modong.backend.auth.Dto.LoginRequest;
import com.modong.backend.auth.Dto.TokenResponse;
import com.modong.backend.domain.club.ClubController;
import com.modong.backend.domain.club.Dto.ClubRequest;
import com.modong.backend.unit.base.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ClubController.class)
public class ClubControllerTest extends ControllerTest {
  private String requestBody;
  private ClubRequest clubRequest;
  @DisplayName("클럽 생성 성공 - 유효한 정보의 클럽 생성 요청이 오면 회원가입 페이지 주소와 함께 상태값 201을 반환해야 한다.")
  @WithMockUser
  @Test
  public void returnSavedIdWithStatusCREATEDIfClubRequestValid() throws Exception {
    // given
    Long response = CLUB_ID;

    clubRequest = new ClubRequest(CLUB_NAME,CLUB_PROFILE_IMG_URL);
    requestBody = objectMapper.writeValueAsString(clubRequest);

    given(clubService.save(any()))
        .willReturn(response);

    // when
    ResultActions perform = mockMvc.perform(post("/api/v1/club")
        .contentType(MediaType.APPLICATION_JSON).with(csrf())
        .content(requestBody));

    // then
    perform
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(redirectedUrl("/api/v1/register"))
        .andExpect(jsonPath("data.id").value(response));
  }
}
