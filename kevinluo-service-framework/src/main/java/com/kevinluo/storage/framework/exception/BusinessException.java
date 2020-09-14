package com.kevinluo.storage.framework.exception;


import com.kevinluo.storage.framework.beans.ErrorResult;
import com.kevinluo.storage.framework.beans.HttpCode;
import com.kevinluo.storage.framework.beans.SystemErrorResult;
import org.apache.logging.log4j.util.Strings;

import java.util.function.Consumer;

/**
 * 通用的业务异常类 BusinessException
 * (json形式返回值同JsonResult，便于前端统一处理)
 */
public class BusinessException extends RuntimeException
{

  private String msg;
  private int code = 500;

  private Object data;

  public BusinessException()
  {
    this(SystemErrorResult.SERVICE_UNKNOWN.getAdvice());
  }

  public BusinessException(Throwable e)
  {
    this(HttpCode.STATUS_500, e.getMessage());
  }

  public BusinessException(ErrorResult eEnum, Throwable e)
  {
    this(eEnum.getCode(), eEnum.getAdvice(), e, true, true, null);
  }

  public BusinessException(String message, Throwable e)
  {
    this(null, message, e, true, true, null);
  }

  public BusinessException(ErrorResult eEnum)
  {
    this(eEnum, null);
  }

  public BusinessException(ErrorResult eEnum, Object conflicts)
  {
    this(eEnum.getCode(), eEnum.getAdvice(), conflicts);
  }

  public BusinessException(int code, String message)
  {
    this(code, message, null, true, true, null);
  }

  public BusinessException(int code, String message, Object conflicts)
  {
    this(code, message, null, true, true, conflicts);
  }

  public BusinessException(String message)
  {
    this(null, message, null, true, true, null);
  }

  public BusinessException(Integer code, String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace, Object data)
  {
    super(message, cause, enableSuppression, writableStackTrace);
    if (message != null)
    {
      this.msg = message;
    }
    if (code != null)
    {
      this.code = code;
    }
    if (data != null)
    {
      this.data = data;
    }
  }

  @Override
  public String getMessage()
  {
    if (Strings.isNotBlank(msg))
    {
      return msg;
    }
    return super.getMessage();
  }

  public Integer getCode()
  {
    return code;
  }

  public static boolean ignoreException(Runnable r)
  {
    return ignoreException(r, null, null);
  }

  public static boolean ignoreException(Runnable r, Runnable f)
  {
    return ignoreException(r, f, null);
  }

  public String getMsg()
  {
    return msg;
  }

  public void setMsg(String msg)
  {
    this.msg = msg;
  }

  public void setCode(int code)
  {
    this.code = code;
  }

  public Object getData()
  {
    return data;
  }

  public void setData(Object data)
  {
    this.data = data;
  }

  /**
   * 忽略异常
   *
   * @param r 代码块
   * @param f finally块
   * @param e cache块
   */
  public static boolean ignoreException(Runnable r, Runnable f, Consumer<Throwable> e)
  {
    try
    {
      r.run();
      return true;
    } catch (Throwable throwable)
    {
      if (e != null)
      {
        e.accept(throwable);
      }
      return false;
    } finally
    {
      if (f != null)
      {
        f.run();
      }
    }
  }

}
