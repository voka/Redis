package com.modong.backend.Enum;

public enum ApplicantStatus {
  Fail(1),Accept(2),Application(3),Interview(4),Success(5);

  private int code;

  ApplicantStatus(int code) {
    this.code = code;
  }

  public static ApplicantStatus valueOf(int code) {
    ApplicantStatus applicantStatus = null;
    switch (code) {
      case 1:
        applicantStatus = ApplicantStatus.Fail;
        break;
      case 2:
        applicantStatus = ApplicantStatus.Accept;
        break;
      case 3:
        applicantStatus = ApplicantStatus.Application;
        break;
      case 4:
        applicantStatus = ApplicantStatus.Interview;
        break;
      case 5:
        applicantStatus = ApplicantStatus.Success;
        break;
      default:
        break;
    }
    return applicantStatus;
  }
}
