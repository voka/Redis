package com.modong.backend.Enum;

public enum StatusCode {
  OPEN(false), CLOSE(true);

  boolean closed;

  StatusCode(boolean closed) {
    this.closed = closed;
  }
}
