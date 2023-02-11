package com.modong.backend.domain.evaluation.dto;

import com.modong.backend.auth.member.Member;
import com.modong.backend.domain.evaluation.Evaluation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "평가 조회")
public class EvaluationResponse {
  @Schema(description = "평가 id", example = "1")
  private Long id;

  @Schema(description = "지원서 id", example = "2")
  private Long applicationId;

  @Schema(description = "지원자 id", example = "3")
  private Long applicantId;
  @Schema(description = "작성자 id", example = "4")
  private Long writerId;
  @Schema(description = "작성자 회원 아이디", example = "test123")
  private String writerMemberId;
  @Schema(description = "작성자 회원 이름", example = "tester")
  private String writerName;
  @Schema(description = "평가 내용", example = "면접 질문 : 나이가 어떻게 되시나요?")
  private String content;
  public EvaluationResponse(Evaluation evaluation, Member member, Long applicationId, Long applicantId) {
    this.id = evaluation.getId();
    this.writerId = evaluation.getCreatorId();
    this.writerMemberId = member.getMemberId();
    this.writerName = member.getName();
    this.content = evaluation.getComment();
    this.applicantId = applicantId;
    this.applicationId = applicationId;
  }
}
