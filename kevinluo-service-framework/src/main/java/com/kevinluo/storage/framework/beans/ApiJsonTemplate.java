package com.kevinluo.storage.framework.beans;

/*
 * Creates on 2020/5/11.
 */

import com.kevinluo.storage.framework.exception.BusinessException;
import lombok.Data;

/**
 * 统一json返回对象
 *
 * @author lts
 */
@Data
@SuppressWarnings("UnusedReturnValue")
public class ApiJsonTemplate
{

  // 默认返回状态码
  private Integer code = 200;

  // 返回数据信息
  private Object data;

  // 建议处理方式
  private String advice = "操作成功";

  // 错误信息
  private String error;

  public ApiJsonTemplate()
  {
    this(null, null, null);
  }

  public ApiJsonTemplate(Object object)
  {
    this(null, null, object);
  }

  public ApiJsonTemplate(ErrorResult errorResult)
  {
    this(errorResult.getCode(), errorResult.getAdvice(), null);
  }

  public ApiJsonTemplate(Integer code, String message)
  {
    this(code, message, null);
  }

  public ApiJsonTemplate(Integer code, String message, Object object)
  {
    if (code == null)
    {
      setJsonResult(SystemErrorResult.SUCCESS);
    } else
    {
      setAdvice(message);
      setCode(code);
    }
    setData(object);
  }

  public ApiJsonTemplate setJsonResult(BusinessException e)
  {
    setJsonResult(e.getCode(), e.getMessage());
    return this;
  }

  public ApiJsonTemplate setJsonResult(ErrorResult err)
  {
    setJsonResult(err.getCode(), err.getAdvice());
    return this;
  }

  public ApiJsonTemplate setJsonResult(ErrorResult err, String error)
  {
    setJsonResult(err.getCode(), err.getAdvice(), error);
    return this;
  }

  public ApiJsonTemplate setJsonResult(int code, String message)
  {
    setJsonResult(code, message, null);
    return this;
  }

  public ApiJsonTemplate setJsonResult(int code, String message, String error)
  {
    setCode(code);
    setAdvice(message);
    setError(error);
    return this;
  }

}