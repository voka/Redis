package com.modong.backend.unit.auth;

import com.modong.backend.auth.AuthService;
import com.modong.backend.auth.Dto.LoginRequest;
import com.modong.backend.auth.Dto.MemberRegisterRequest;
import com.modong.backend.auth.Dto.TokenResponse;
import com.modong.backend.auth.JwtTokenProvider;
import com.modong.backend.auth.member.Member;
import com.modong.backend.unit.base.ServiceTest;
import com.modong.backend.utils.UUIDGenerateUtils;
import java.util.Optional;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest extends ServiceTest {

  private Faker faker = new Faker();

  @Autowired
  private AuthService authService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  private Member member;

  private String memberId,password;
  @BeforeEach
  public void init(){

    memberId = "member1234";
    password = "myPassword1234!";

    MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.
        builder().memberId("memberId").email("email@naver.com").password("password")
        .phone("01032343243").name("testMember")
        .clubCode(UUIDGenerateUtils.makeShortUUID()).build();

    member = new Member(memberRegisterRequest);
    member.setEncodedPassword(passwordEncoder.encode(password));
  }

  // 두 토큰 => ( access, refresh )




  @DisplayName("로그인 요청이 정상이고, 최초 로그인시 토큰이 발급되어야 한다.")
  @Test
  public void returnTokenIfLoginRequestIsValid(){
    //given
    LoginRequest loginRequest = new LoginRequest(memberId,password);
    given(memberRepository.findByMemberId(anyString()))
        .willReturn(Optional.of(member));

    //when
    TokenResponse tokenResponse = authService.login(loginRequest);

    //then
    assertNotNull(tokenResponse);
  }


}
