package com.kevinluo.storage.framework.exception;

/*
 * creates on 2020/5/11 20:32.
 */

/**
 * @author lts
 */
public class LogException extends BaseException {

  public LogException() {
  }

  public LogException(String message) {
    super(message);
  }

  public LogException(String message, Throwable cause) {
    super(message, cause);
  }

  public LogException(Throwable cause) {
    super(cause);
  }

  public LogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
