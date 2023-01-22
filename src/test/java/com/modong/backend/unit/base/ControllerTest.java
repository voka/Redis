package com.modong.backend.unit.base;

import static com.modong.backend.Fixtures.ClubFixture.CLUB_CODE;
import static com.modong.backend.Fixtures.MemberFixture.EMAIL;
import static com.modong.backend.Fixtures.MemberFixture.MEMBER_ID;
import static com.modong.backend.Fixtures.MemberFixture.NAME;
import static com.modong.backend.Fixtures.MemberFixture.PASSWORD;
import static com.modong.backend.Fixtures.MemberFixture.PHONE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modong.backend.auth.AuthService;
import com.modong.backend.auth.Dto.LoginRequest;
import com.modong.backend.auth.Dto.MemberRegisterRequest;
import com.modong.backend.auth.member.MemberService;
import com.modong.backend.domain.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
public class ControllerTest {

  @MockBean
  protected MemberService memberService;

  @MockBean
  protected ClubService clubService;

  @MockBean
  protected AuthService authService;

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;


  protected MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.builder()
      .memberId(MEMBER_ID).email(EMAIL).password(PASSWORD)
      .phone(PHONE).name(NAME).clubCode(CLUB_CODE).build();


}
