package com.ap.autocompiler.compilation.model;

public class Error {

  private Integer lineNumer;
  private String message;

  public Error(Integer lineNumer, String message) {
    this.lineNumer = lineNumer;
    this.message = message;
  }

  public Integer getLineNumer() {
    return this.lineNumer;
  }

  public void setLineNumer(Integer lineNumer) {
    this.lineNumer = lineNumer;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return (
      "{" +
      " lineNumer='" +
      getLineNumer() +
      "'" +
      ", message='" +
      getMessage() +
      "'" +
      "}"
    );
  }
}
