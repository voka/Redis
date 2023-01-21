package com.modong.backend.unit.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.modong.backend.auth.Dto.MemberRegisterRequest;
import com.modong.backend.auth.member.Dto.MemberCheckRequest;
import com.modong.backend.auth.member.Member;
import com.modong.backend.auth.member.MemberService;
import com.modong.backend.domain.club.Club;
import com.modong.backend.domain.club.Dto.ClubRequest;
import com.modong.backend.global.exception.club.ClubNotFoundException;
import com.modong.backend.global.exception.member.DuplicateMemberIdException;
import com.modong.backend.global.exception.member.MemberNotFoundException;
import com.modong.backend.unit.base.ServiceTest;
import com.modong.backend.utils.UUIDGenerateUtils;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;


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

  }

  @DisplayName("중복 ID 체크 - MemberId가 중복이면 DuplicateMemberIdException 가 발생해야 한다.")
  @Test
  public void throwExceptionIfMemberIdExist(){
    //given,when
    MemberCheckRequest memberCheckRequest = MemberCheckRequest.builder().memberId(memberId).build();

    given(memberRepository.existsByMemberId(memberId))
        .willReturn(true);

    //then
    assertThatThrownBy(() -> memberService.checkMemberId(memberCheckRequest))
        .isInstanceOf(DuplicateMemberIdException.class);
  }

  @DisplayName("중복 ID 체크  - MemberId가 중복되지 않으면 예외가 발생하지 않아야 한다.")
  @Test
  public void passIfMemberIdNotExist(){
    //given,when
    MemberCheckRequest memberCheckRequest = MemberCheckRequest.builder().memberId(memberId).build();
    given(memberRepository.existsByMemberId(memberId))
        .willReturn(false);
    //then
    assertThatCode(() -> memberService.checkMemberId(memberCheckRequest))
        .doesNotThrowAnyException();
  }
  @DisplayName("회원가입 - 동아리코드가 유효하면 정상적으로 가입이 완료돼야 한다. ")
  @Test
  public void returnIdIfRegisterRequestIsValid(){
    //given

    Club club = new Club(clubRequest);

    Member member = new Member(memberRegisterRequest);
    member.setEncodedPassword(passwordEncoder.encode(memberRegisterRequest.getPassword()));

    Long fakeMemberId = 1L;
    ReflectionTestUtils.setField(member,"id",fakeMemberId);

    //동아리 코드 유효
    given(clubRepository.findByClubCode(memberRegisterRequest.getClubCode()))
        .willReturn(Optional.of(club));
    //중복 이메일
    given(memberRepository.existsByEmail(memberRegisterRequest.getEmail()))
        .willReturn(false);
    //중복 휴대폰
    given(memberRepository.existsByPhone(memberRegisterRequest.getPhone()))
        .willReturn(false);

    //회원 저장시 반환값
    given(memberRepository.save(any()))
        .willReturn(member);


    //when
    Long savedId = memberService.register(memberRegisterRequest);

    //then
    assertThatCode(() -> memberService.register(memberRegisterRequest))
        .doesNotThrowAnyException();

    assertThat(savedId).isNotNull();
  }

  @DisplayName("회원가입 - 동아리 코드가 유효하지 않으면 ClubNotFoundException 이 발생해야 한다. ")
  @Test
  public void throwErrorIFClubCodeIsNotValid(){
    //given,when

    //동아리 코드 유효 X
    given(clubRepository.findByClubCode(memberRegisterRequest.getClubCode()))
        .willReturn(Optional.empty());

    //when
    assertThatThrownBy(() -> memberService.register(memberRegisterRequest))
        .isInstanceOf(ClubNotFoundException.class);

  }

  @DisplayName("회원 조회 - Id를 가진 동아리가 존재하지 않으면 ClubNotFoundException 가 발생한다..")
  @Test
  public void ThrowExceptionIfClubIdNotExist(){
    //given, when

    Long findId = 1L;

    given(clubRepository.findById(any()))
        .willReturn(Optional.empty());


    //then
    assertThatThrownBy(() -> memberService.findById(findId))
        .isInstanceOf(MemberNotFoundException.class);
  }

}
