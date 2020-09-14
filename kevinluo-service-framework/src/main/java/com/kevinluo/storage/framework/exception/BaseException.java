package com.kevinluo.storage.framework.exception;

/*
 * Creates on 2019/11/13.
 */

/**
 * @author lts
 */
public class BaseException extends RuntimeException
{

  public BaseException()
  {
  }

  public BaseException(String message)
  {
    super(message);
  }

  public BaseException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public BaseException(Throwable cause)
  {
    super(cause);
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
