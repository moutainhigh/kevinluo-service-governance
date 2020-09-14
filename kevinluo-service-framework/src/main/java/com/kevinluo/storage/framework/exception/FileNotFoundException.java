package com.kevinluo.storage.framework.exception;

/*
 * Creates on 2019/11/13.
 */

/**
 * @author lts
 */
public class FileNotFoundException extends BaseException
{

  public FileNotFoundException()
  {
  }

  public FileNotFoundException(String message)
  {
    super(message);
  }

  public FileNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public FileNotFoundException(Throwable cause)
  {
    super(cause);
  }

  public FileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
