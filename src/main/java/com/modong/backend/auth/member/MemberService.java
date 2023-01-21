package com.modong.backend.auth.member;

import com.modong.backend.auth.Dto.MemberRegisterRequest;
import com.modong.backend.auth.member.Dto.MemberCheckRequest;
import com.modong.backend.domain.club.Club;
import com.modong.backend.domain.club.ClubCheckRequest;
import com.modong.backend.domain.club.ClubRepository;
import com.modong.backend.domain.club.clubMemeber.ClubMember;
import com.modong.backend.global.exception.club.ClubNotFoundException;
import com.modong.backend.global.exception.member.DuplicateEmailException;
import com.modong.backend.global.exception.member.DuplicateMemberIdException;
import com.modong.backend.global.exception.member.DuplicatePhoneException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;

  private final ClubRepository clubRepository;

  private final PasswordEncoder passwordEncoder;
  public Long register(MemberRegisterRequest memberRegisterRequest) {
    //휴대폰 번호 중복 검사
    if(isDuplicatePhone(memberRegisterRequest.getPhone())){
      throw new DuplicatePhoneException();
    }
    //이메일 중복 검사
    if(isDuplicateEmail(memberRegisterRequest.getEmail())){
      throw new DuplicateEmailException();
    }

    Club club = clubRepository.findByClubCode(memberRegisterRequest.getClubCode())
        .orElseThrow(() -> new ClubNotFoundException(memberRegisterRequest.getClubCode()));

    Member member = new Member(memberRegisterRequest);

    member.setEncodedPassword(passwordEncoder.encode(memberRegisterRequest.getPassword()));

    ClubMember clubMember = new ClubMember(club,member);

    member.addClub(clubMember);


    try{
      memberRepository.save(member);
      return member.getId();
    } catch (Exception e){
      throw new DuplicateMemberIdException();
    }
  }

  public void checkMemberId(MemberCheckRequest memberCheckRequest){
    if(isDuplicateMemberId(memberCheckRequest.getMemberId())){
      throw new DuplicateMemberIdException();
    }
  }
  public void checkClubCode(ClubCheckRequest clubCheckRequest){
    if(isExistClubCode(clubCheckRequest.getClubCode())){
      throw new ClubNotFoundException(clubCheckRequest.getClubCode());
    }
  }

  private boolean isExistClubCode(String clubCode) {
    return clubRepository.existsByClubCode(clubCode);
  }

  private boolean isDuplicateEmail(String email) {
    return memberRepository.existsByEmail(email);
  }

  private boolean isDuplicatePhone(String phone) {
    return memberRepository.existsByPhone(phone);
  }

  private boolean isDuplicateMemberId(String memberId) {
    return memberRepository.existsById(memberId);
  }
}
