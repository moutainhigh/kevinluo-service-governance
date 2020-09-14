
package com.kevinluo.storage.framework.spring;

import com.kevinluo.storage.framework.beans.ApiJsonTemplate;
import com.kevinluo.storage.framework.beans.HttpCode;
import com.kevinluo.storage.framework.exception.BusinessException;
import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常统一处理的默认实现 （继承自该类并添加@ControllerAdvice注解即可自动支持兼容页面和JSON的异常处理）
 */
@RestControllerAdvice
public class MyExceptionHandler
{

  private final static Log log = LogFactory.getLog(MyExceptionHandler.class);

  public MyExceptionHandler()
  {
  }

  /**
   * 统一异常处理类
   */
  @ExceptionHandler(Exception.class)
  public Object handleException(HttpServletRequest request, Exception e)
  {
    log.error(e, e.getCause(),e.getStackTrace()[0]);
    ApiJsonTemplate template = new ApiJsonTemplate();
    if (e instanceof BusinessException)
    {
			BusinessException ex = (BusinessException) e;
			return template.setJsonResult(ex);
    } else
    {
      return template.setJsonResult(HttpCode.STATUS_500, e.getMessage());
    }
  }

  @ExceptionHandler(value = BusinessException.class)
  @ResponseBody
  public ApiJsonTemplate customExceptionHandler(BusinessException e)
  {
    log.error("系统异常信息：" + e.getMessage());
    int code = 500;
    if (e.getCode() != null)
    {
      code = e.getCode();
    }
    e.printStackTrace();
    return new ApiJsonTemplate(code, e.getMessage());
  }

}
