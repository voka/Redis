package com.modong.backend.auth;

import com.modong.backend.auth.Dto.LoginRequest;
import com.modong.backend.auth.Dto.TokenResponse;
import com.modong.backend.auth.member.Member;
import com.modong.backend.auth.member.MemberRepository;
import com.modong.backend.auth.refreshToken.RefreshTokenRepository;
import com.modong.backend.global.exception.auth.PasswordMismatchException;
import com.modong.backend.global.exception.member.MemberNotFoundException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final MemberRepository memberRepository; // readOnly
  private final RefreshTokenRepository refreshTokenRepository;//read and write

  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  public TokenResponse login(LoginRequest loginRequest) {
    //멤버 존재하는지 확인
    Member findMember = memberRepository.findByMemberId(loginRequest.getMemberId()).orElseThrow(() ->
        new MemberNotFoundException(loginRequest.getMemberId()));
    //비밀번호 검증
    validatePassword(findMember,loginRequest.getPassword());
    // 도큰 두개 반환
    return new TokenResponse(issueAccessToken(findMember),issueRefreshToken(findMember));
  }


  private String issueAccessToken(final Member findMember) {
    return jwtTokenProvider.createAccessToken(findMember.getId());
  }
  private String issueRefreshToken(final Member findMember) {
    return jwtTokenProvider.createAccessToken(findMember.getId());
  }


  private void validatePassword(final Member findMember, final String password) {
    if (!passwordEncoder.matches(password, findMember.getPassword())) {
      throw new PasswordMismatchException();
    }
  }


}
