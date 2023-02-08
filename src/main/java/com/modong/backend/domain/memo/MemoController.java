package com.modong.backend.domain.memo;

import com.modong.backend.Enum.CustomCode;
import com.modong.backend.auth.support.Auth;
import com.modong.backend.base.Dto.BaseResponse;
import com.modong.backend.base.Dto.SavedId;
import com.modong.backend.domain.applicant.Dto.ApplicantCreateRequest;
import com.modong.backend.domain.memo.dto.MemoCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "지원자 메모 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemoController {

  private final MemoService memoService;

  @PostMapping("/memo")//메모 생성 API
  @Operation(summary = "메모 생성", description = "동아리 회원이 지원자를 보고 남긴 메모를 저장한다.", responses = {
      @ApiResponse(responseCode = "201", description = "메모 생성 성공", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity createApplicantAndSaveQuestions(@Validated @RequestBody MemoCreateRequest memoCreateRequest, @Auth Long memberId){
    SavedId savedId = new SavedId(memoService.create(memoCreateRequest,memberId));
    return ResponseEntity.created(URI.create("/api/v1/memo/" + savedId.getId())).body(new BaseResponse(savedId,HttpStatus.CREATED.value(),CustomCode.SUCCESS_CREATE));
  }

}
