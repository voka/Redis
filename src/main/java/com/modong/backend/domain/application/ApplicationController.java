package com.modong.backend.domain.application;

import com.modong.backend.domain.application.Dto.ApplicationDetailResponse;
import com.modong.backend.domain.application.Dto.ApplicationRequest;
import com.modong.backend.domain.application.Dto.ApplicationSimpleResponse;
import com.modong.backend.base.Dto.BaseResponse;
import com.modong.backend.base.Dto.SavedId;
import com.modong.backend.Enum.MessageCode;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "지원서 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApplicationController {
  private final ApplicationService applicationService;

  //동아리 전체 지원서 조회
  @GetMapping("/applications/{club_id}")
  @Operation(summary = "동아리의 전체 지원서 조회", description = "동아리의 ID를 이용해 작성한 모든 지원서를 조회한다.", responses = {
      @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
  })
  @Parameter(name = "id", description = "동아리 ID", required = true, example = "1")
  public ResponseEntity getApplicationsByClubId(@Valid @PathVariable(name="club_id") Long clubId){
    List<ApplicationSimpleResponse> applications = applicationService.findAllByClubId(clubId);
    return ResponseEntity.ok(new BaseResponse(applications, HttpStatus.OK.value(), MessageCode.SUCCESS_GET_LIST));
  }
  //지원서 조회
  @GetMapping("/application/{application_id}")
  @Operation(summary = "지원서 조회", description = "지원서의 ID를 이용해 작성한 지원서를 조회한다.")
  @Parameter(name = "id", description = "지원서 ID", required = true, example = "1")
  public ResponseEntity getApplicationById(@Valid @PathVariable(name = "application_id") Long applicationId){
    ApplicationDetailResponse application = applicationService.findDetailById(applicationId);
    return ResponseEntity.ok(new BaseResponse(application,HttpStatus.OK.value(),MessageCode.SUCCESS_GET));
  }
  //지원서 조회(
  @GetMapping("/view/application/{url_id}")
  @Operation(summary = "지원서 조회", description = "지원서의 링크 아이디를 이용해 작성한 지원서를 조회한다.")
  @Parameter(name = "urlId", description = "지원서 url ID ", required = true, example = "uH9wk72MTr")
  public ResponseEntity getApplicationByUrlId(@Valid @PathVariable(name = "url_id") String urlId){
    ApplicationDetailResponse application = applicationService.findDetailByUrlId(urlId);
    return ResponseEntity.ok(new BaseResponse(application,HttpStatus.OK.value(),MessageCode.SUCCESS_GET));
  }

  //지원서 생성
  @PostMapping("/application")
  @Operation(summary = "지원서를 생성한다.", description = "지원서를 생성한다.")
  public ResponseEntity createApplication(@Valid @RequestBody ApplicationRequest applicationRequest){
    SavedId savedId = new SavedId(applicationService.createApplication(applicationRequest));
    return ResponseEntity.ok(new BaseResponse(savedId, HttpStatus.CREATED.value(), MessageCode.SUCCESS_CREATE));
  }

  //지원서 수정(필수 질문 부분)
  @PutMapping("/application/{application_id}")
  @Operation(summary = "지원서 수정", description = "지원서의 ID를 이용해 작성한 지원서를 수정한다.")
  @Parameter(name = "id", description = "지원서 ID", required = true, example = "1")
  public ResponseEntity updateEssentialQuestion(@Valid @PathVariable(name = "application_id") Long applicationId, @RequestBody ApplicationRequest applicationRequest){
    SavedId savedId = new SavedId(applicationService.updateEssentialQuestion(applicationId,
        applicationRequest));
    return ResponseEntity.ok(new BaseResponse(savedId, HttpStatus.OK.value(), MessageCode.SUCCESS_UPDATE));

  }

  //지원서 삭제
  @DeleteMapping("/application/{application_id}")
  @Operation(summary = "지원서 삭제", description = "지원서의 ID를 이용해 작성한 지원서를 삭제한다.")
  @Parameter(name = "id", description = "지원서 ID", required = true, example = "1")
  public ResponseEntity deleteApplicationById(@Valid @PathVariable(name = "application_id") Long applicationId) {
    applicationService.deleteApplication(applicationId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), MessageCode.SUCCESS_DELETE));
  }
}
