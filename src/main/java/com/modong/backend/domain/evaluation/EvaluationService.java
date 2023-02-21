package com.modong.backend.domain.evaluation;

import com.modong.backend.auth.member.Member;
import com.modong.backend.auth.member.MemberRepository;
import com.modong.backend.domain.applicant.Applicant;
import com.modong.backend.domain.applicant.repository.ApplicantRepository;
import com.modong.backend.domain.applicant.repository.ApplicantRepositoryCustomImpl;
import com.modong.backend.domain.application.Application;
import com.modong.backend.domain.club.clubMemeber.ClubMember;
import com.modong.backend.domain.evaluation.dto.*;
import com.modong.backend.global.exception.ResourceNotFoundException;
import com.modong.backend.global.exception.applicant.ApplicantNotFoundException;
import com.modong.backend.global.exception.auth.NoPermissionCreateException;
import com.modong.backend.global.exception.auth.NoPermissionDeleteException;
import com.modong.backend.global.exception.auth.NoPermissionReadException;
import com.modong.backend.global.exception.auth.NoPermissionUpdateException;
import com.modong.backend.global.exception.evaluation.AlreadyExistsException;
import com.modong.backend.global.exception.member.MemberNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EvaluationService {
  private final EvaluationRepository evaluationRepository;
  private final MemberRepository memberRepository;
  private final ApplicantRepository applicantRepository;
  private final ApplicantRepositoryCustomImpl applicantRepositoryCustomImpl;

  @Transactional
  public Long create(EvaluationCreateRequest evaluationCreateRequest, Long memberId) {
    Long applicantId = evaluationCreateRequest.getApplicantId();

    //회원 조회 실패시 에러 반환
    Member member = memberRepository.findByIdAndIsDeletedIsFalse(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
    //지원자 조회 실패시 에러 반환
    Applicant applicant = applicantRepository.findByIdAndIsDeletedIsFalse(applicantId).orElseThrow(() -> new ApplicantNotFoundException(applicantId));

    //지원서 조회 실패시 에러 반환
    Application application = applicant.getApplication();//applicationRepository.findByIdAndIsDeletedIsFalse(applicationId).orElseThrow(() -> new ApplicationNotFoundException(applicationId));
    //이미 평가한 내용이 있다면 에러 반환
    if(evaluationRepository.existsByApplicantIdAndMemberIdAndIsDeletedIsFalse(applicantId,memberId)){
      throw new AlreadyExistsException("평가");
    }
    //동아리 Id 조회
    Long memberClubId = application.getClub().getId();

    //회원이 지원자에 대해 평가할 권한이 있는지를 확인하기 위해 동아리가 같은지 비교
    for(ClubMember clubMember : member.getClubs()){
      if(memberClubId.equals(clubMember.getClub().getId())){
        Evaluation evaluation = new Evaluation(evaluationCreateRequest,member,applicant,memberClubId);
        Evaluation saved = evaluationRepository.save(evaluation);
        applicantRepositoryCustomImpl.updateRateByApplicantId(applicantId);
        return saved.getId();
      }
    }
    throw new NoPermissionCreateException();
  }

  @Transactional
  public Long update(EvaluationUpdateRequest evaluationUpdateRequest, Long evaluationId, Long memberId) {
    //회원 조회 실패시 에러 반환
    Member member = memberRepository.findByIdAndIsDeletedIsFalse(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

    //평가 조회 실패시 에러 반환
    Evaluation evaluation = evaluationRepository.findByIdAndIsDeletedIsFalse(evaluationId).orElseThrow(() -> new ResourceNotFoundException("Evaluation",evaluationId));

    //작성자가 회원이 맞는지 검증
    if(evaluation.isWriter(member)){
      evaluation.update(evaluationUpdateRequest);
      Evaluation saved = evaluationRepository.save(evaluation);
      applicantRepositoryCustomImpl.updateRateByApplicantId(evaluation.getApplicant().getId());
      return saved.getId();
    }
    else throw new NoPermissionUpdateException();
  }

  @Transactional
  public void delete(Long evaluationId, Long memberId) {
    //회원 조회 실패시 에러 반환
    Member member = memberRepository.findByIdAndIsDeletedIsFalse(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

    //평가 조회 실패시 에러 반환
    Evaluation evaluation = evaluationRepository.findByIdAndIsDeletedIsFalse(evaluationId).orElseThrow(() -> new ResourceNotFoundException("Evaluation",evaluationId));


    //작성자가 회원이 맞는지 검증
    if(evaluation.isWriter(member)){
      evaluation.delete();
      evaluationRepository.save(evaluation);
      applicantRepositoryCustomImpl.updateRateByApplicantId(evaluation.getApplicant().getId());
    }
    else throw new NoPermissionDeleteException();
  }
  public List<EvaluationResponse> findAllByApplication(EvaluationFindRequest evaluationFindRequest, Long memberId) {
    Long applicantId = evaluationFindRequest.getApplicantId();
    //회원 조회 실패시 에러 반환
    Member member = memberRepository.findByIdAndIsDeletedIsFalse(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
    //지원자 조회 실패시 에러 반환
    Applicant applicant = applicantRepository.findByIdAndIsDeletedIsFalse(applicantId).orElseThrow(() -> new ApplicantNotFoundException(applicantId));
    //지원서 조회
    Application application = applicant.getApplication();//applicationRepository.findByIdAndIsDeletedIsFalse(applicationId).orElseThrow(() -> new ApplicationNotFoundException(applicationId));

    Long clubId = application.getClub().getId();

    boolean havePermission = false;
    List<Evaluation> evaluations = new ArrayList<>();

    //회원이 지원자에 대한 평가를 조회할 권한이 있는지를 동아리가 같은지 비교
    for(ClubMember clubMember : member.getClubs()){
      if(clubId.equals(clubMember.getClub().getId())){
        havePermission = true;
        break;
      }
    }

    if(havePermission){
      List<EvaluationResponse> result = new ArrayList<>();
      evaluations = evaluationRepository.findAllByApplicantIdAndIsDeletedIsFalse(applicant.getId());
      for(Evaluation evaluation : evaluations){
        Member writer = evaluation.getMember();
        result.add(new EvaluationResponse(evaluation,writer,application.getId(),applicant.getId()));
      }
      return result;
    }
    else{
      throw new NoPermissionReadException();
    }
  }

    public EvaluationResponse findById(Long evaluationId, Long memberId) {
      //회원 조회 실패시 에러 반환
      Member member = memberRepository.findByIdAndIsDeletedIsFalse(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

      //평가 조회 실패시 에러 반환
      Evaluation evaluation = evaluationRepository.findByIdAndIsDeletedIsFalse(evaluationId).orElseThrow(() -> new ResourceNotFoundException("평가",evaluationId));

      Long clubId = evaluation.getClubId();

      boolean havePermission = false;

      //회원이 지원자에 대한 평가를 조회할 권한이 있는지를 동아리가 같은지 비교
      for(ClubMember clubMember : member.getClubs()){
        if(clubId.equals(clubMember.getClub().getId())){
          havePermission = true;
          break;
        }
      }

      if(havePermission){
        return new EvaluationResponse(evaluation,member,evaluation.getApplicant().getApplication().getId(),evaluation.getApplicant().getId());
      }
      else{
        throw new NoPermissionReadException();
      }
    }

  public boolean check(EvaluationCheckRequest evaluationCheckRequest, Long memberId) {
    Long applicantId = evaluationCheckRequest.getApplicantId();
    //회원 조회 실패시 에러 반환
    Member member = memberRepository.findByIdAndIsDeletedIsFalse(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

    //지원자 조회 실패시 에러 반환
    Applicant applicant = applicantRepository.findByIdAndIsDeletedIsFalse(applicantId).orElseThrow(() -> new ApplicantNotFoundException(applicantId));

    return evaluationRepository.existsByApplicantIdAndMemberIdAndIsDeletedIsFalse(applicant.getId(),memberId);
  }
}