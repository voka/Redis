package com.modong.backend.Base.Dto;

import lombok.Getter;

@Getter
public class SavedId {
  private Long id;

  public SavedId(Long id) {
    this.id = id;
  }
}
