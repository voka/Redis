package com.modong.backend.Enum;

public enum QuestionType {
  QUESTION(1), CHECKBOX_QUESTION(2), RADIO_QUESTION(3);

  private int code;

  QuestionType(int code) {
    this.code = code;
  }

  public static QuestionType findByCode(int code) {
    QuestionType questionType = null;
    switch (code) {
      case 1:
        questionType = QuestionType.QUESTION;
        break;
      case 2:
        questionType = QuestionType.CHECKBOX_QUESTION;
        break;
      case 3:
        questionType = QuestionType.RADIO_QUESTION;
        break;
      default:
        break;
    }
    return questionType;
  }

  public int getCode() {
    return this.code;
  }
}
