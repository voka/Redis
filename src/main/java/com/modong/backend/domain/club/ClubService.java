package com.modong.backend.domain.club;

import static com.modong.backend.Enum.CustomCode.ERROR_REQ;
import static com.modong.backend.Enum.CustomCode.ERROR_REQ_PARAM_ID;

import com.modong.backend.global.exception.NotFoundException;
import com.modong.backend.global.exception.club.ClubNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

  private final ClubRepository clubRepository;
  public Club findById(Long clubId) {
    Club club = clubRepository.findById(clubId).orElseThrow(() -> new IllegalArgumentException(ERROR_REQ_PARAM_ID.toString()));
    return club;
  }
  public Club findByClubCode(String clubCode) {
    Club club = clubRepository.findByClubCode(clubCode).orElseThrow(() -> new ClubNotFoundException(clubCode));
    return club;
  }
}
