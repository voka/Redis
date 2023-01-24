package com.modong.backend.domain.club;

import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy.Base;
import com.modong.backend.Enum.CustomCode;
import com.modong.backend.base.Dto.BaseResponse;
import com.modong.backend.base.Dto.SavedId;
import com.modong.backend.domain.club.Dto.ClubRequest;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "동아리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClubController {

  private final ClubService clubService;

  @PostMapping("/club")
  @Operation(summary = "동아리 생성", description = "동아리를 생성한다.")
  public ResponseEntity saveClub(@RequestBody @Valid ClubRequest clubRequest){
    SavedId savedId = new SavedId(clubService.save(clubRequest));
    return ResponseEntity.created(URI.create("/api/v1/register")).body(new BaseResponse(savedId, HttpStatus.CREATED.value(), CustomCode.SUCCESS_CREATE));
  }

}
