package com.modong.backend.unit.domain.member;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.modong.backend.auth.Dto.MemberRegisterRequest;
import com.modong.backend.auth.member.Dto.MemberCheckRequest;
import com.modong.backend.auth.member.Member;
import com.modong.backend.auth.member.MemberService;
import com.modong.backend.domain.club.Club;
import com.modong.backend.domain.club.ClubCheckRequest;
import com.modong.backend.domain.club.Dto.ClubRequest;
import com.modong.backend.global.exception.club.ClubNotFoundException;
import com.modong.backend.global.exception.member.DuplicateMemberIdException;
import com.modong.backend.unit.base.ServiceTest;
import com.modong.backend.utils.UUIDGenerateUtils;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


// 각 멤버 변수의 유효값 검사는 우리 Controller 에서 처리하는 책임을 가지게 하는 걸로 합니다.
public class MemberServiceTest extends ServiceTest {

  @Autowired
  private MemberService memberService;

  private MemberRegisterRequest memberRegisterRequest;

  private ClubRequest clubRequest;

  private final String memberId = "memberId";

  private final String clubCode = "F1KAO132K";

  @BeforeEach
  public void init(){

    memberRegisterRequest = MemberRegisterRequest.
        builder().memberId(memberId).email("email@naver.com").password("password")
        .phone("01032343243").name("testMember").clubCode(clubCode)
        .clubCode(UUIDGenerateUtils.makeShortUUID()).build();

    clubRequest = ClubRequest.builder().name("모동")
        .profileImgUrl("https://avatars.githubusercontent.com/u/38587274?v=4").build();

    Member member = new Member(memberRegisterRequest);
    member.setEncodedPassword(passwordEncoder.encode(memberRegisterRequest.getPassword()));
  }

  @DisplayName("중복 ID 체크 - MemberId가 중복이면 DuplicateMemberIdException 가 발생해야 한다.")
  @Test
  public void throwExceptionIfMemberIdExist(){
    //given
    MemberCheckRequest memberCheckRequest = MemberCheckRequest.builder().memberId(memberId).build();
    //when
    given(memberRepository.existsById(memberId))
        .willReturn(true);
    //then
    assertThatThrownBy(() -> memberService.checkMemberId(memberCheckRequest))
        .isInstanceOf(DuplicateMemberIdException.class);
  }

  @DisplayName("중복 ID 체크  - MemberId가 중복되지 않으면 예외가 발생하지 말아야 한다.")
  @Test
  public void passIfMemberIdNotExist(){
    //given
    MemberCheckRequest memberCheckRequest = MemberCheckRequest.builder().memberId(memberId).build();
    //when
    given(memberRepository.existsById(memberId))
        .willReturn(false);
    //then
    assertThatCode(() -> memberService.checkMemberId(memberCheckRequest))
        .doesNotThrowAnyException();
  }
  @DisplayName("ClubCode 유효성 체크 - 클럽코드로 등록된 동아리가 없으면 ClubNotFoundException 가 발생해야 한다.")
  @Test
  public void throwExceptionIfClubNotExist(){
    //given
    ClubCheckRequest clubCheckRequest = ClubCheckRequest.builder().ClubCode(clubCode).build();
    //when
    given(clubRepository.existsByClubCode(clubCode))
        .willReturn(true);
    //then
    assertThatThrownBy(() -> memberService.checkClubCode(clubCheckRequest))
        .isInstanceOf(ClubNotFoundException.class);
  }

  @DisplayName("ClubCode 유효성 체크 - 클럽코드로 등록된 동아리가 있으면 예외가 발생하지 말아야 한다.")
  @Test
  public void passIfClubExist(){
    //given
    ClubCheckRequest clubCheckRequest = ClubCheckRequest.builder().ClubCode(clubCode).build();
    //when
    given(clubRepository.existsByClubCode(clubCode))
        .willReturn(false);
    //then
    assertThatCode(() -> memberService.checkClubCode(clubCheckRequest))
        .doesNotThrowAnyException();
  }

  @DisplayName("회원가입 - 동아리코드가 유효하면 정상적으로 가입이 완료돼야 한다. ")
  @Test
  public void returnIdIfRegisterRequestIsValid(){
    //given

    Club club = new Club(clubRequest);

    //동아리 코드 유효
    given(clubRepository.findByClubCode(memberRegisterRequest.getClubCode()))
        .willReturn(Optional.of(club));

    //중복 이메일
    given(memberRepository.existsByEmail(memberRegisterRequest.getEmail()))
        .willReturn(false);
    //중복 휴대폰
    given(memberRepository.existsByPhone(memberRegisterRequest.getPhone()))
        .willReturn(false);

    //when
    assertThatCode(() -> memberService.register(memberRegisterRequest))
        .doesNotThrowAnyException();

  }

  @DisplayName("회원가입 - 동아리 코드가 유효하지 않으면 ClubNotFoundException 이 발생해야 한다. ")
  @Test
  public void throwErrorIFClubCodeIsNotValid(){
    //given

    //동아리 코드 유효 X
    given(clubRepository.findByClubCode(memberRegisterRequest.getClubCode()))
        .willReturn(Optional.empty());

    //중복 이메일
    given(memberRepository.existsByEmail(memberRegisterRequest.getEmail()))
        .willReturn(false);
    //중복 휴대폰
    given(memberRepository.existsByPhone(memberRegisterRequest.getPhone()))
        .willReturn(false);

    //when
    assertThatThrownBy(() -> memberService.register(memberRegisterRequest))
        .isInstanceOf(ClubNotFoundException.class);

  }

}
