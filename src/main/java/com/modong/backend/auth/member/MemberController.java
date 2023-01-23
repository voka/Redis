package com.modong.backend.auth.member;

import com.modong.backend.Enum.CustomCode;
import com.modong.backend.auth.member.Dto.MemberCheckRequest;
import com.modong.backend.auth.member.Dto.MemberRegisterRequest;
import com.modong.backend.auth.support.Auth;
import com.modong.backend.base.Dto.BaseResponse;
import com.modong.backend.base.Dto.SavedId;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags =  "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/register")
  @Operation(summary = "회원가입", description = "동아리 코드 및 회원가입에 필요한 데이터들과 함께 회원가입을 한다.")
  public ResponseEntity saveMember(@RequestBody @Validated MemberRegisterRequest memberRegisterRequest){
    SavedId savedId = new SavedId(memberService.register(memberRegisterRequest));
    return ResponseEntity.created(URI.create("/api/v1/member/" + savedId.getId())).body(new BaseResponse(savedId,HttpStatus.CREATED.value(),CustomCode.SUCCESS_CREATE));
  }

  @GetMapping("/member/{memberId}")
  @Operation(summary = "회원 조회", description = "회원 id로 회원을 조회한다.")
  public ResponseEntity findById(@Auth @PathVariable final Long memberId){
    return ResponseEntity.ok(new BaseResponse(memberService.findById(memberId), HttpStatus.OK.value(), CustomCode.SUCCESS_GET));
  }

  @PostMapping("/member/check")
  @Operation(summary = "id 검사", description = "중복 회원 id 검사")
  public ResponseEntity checkMemberId(@RequestBody @Validated MemberCheckRequest memberCheckRequest){
    return ResponseEntity.ok(new BaseResponse(memberService.checkMemberId(memberCheckRequest), HttpStatus.OK.value(), CustomCode.SUCCESS_CHECK));
  }
}
