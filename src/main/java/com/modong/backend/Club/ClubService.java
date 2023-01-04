package com.modong.backend.Club;

import static com.modong.backend.Enum.MessageCode.ERROR_REQ_PARAM_ID;

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
}
