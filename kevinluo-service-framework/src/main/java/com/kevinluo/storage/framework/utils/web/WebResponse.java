package com.kevinluo.storage.framework.utils.web;

/*
 * Creates on 2019/11/13.
 */

import com.kevinluo.storage.framework.exception.BusinessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lts
 */
public class WebResponse
{

  @SuppressWarnings("ConstantConditions")
  public static HttpServletResponse getHttpServletResponse()
  {
    return ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getResponse();
  }

  public static PrintWriter getWriter()
  {
    try
    {
      return getHttpServletResponse().getWriter();
    } catch (IOException e)
    {
      throw new BusinessException(e);
    }
  }

  /**
   * 向浏览器写入数据
   * @param object 需要写入的数据
   */
  public static void print(Object object)
  {
    PrintWriter writer = getWriter();
    try
    {
      writer.write(String.valueOf(object));
    }catch (Exception e)
    {
      throw new BusinessException(e);
    }finally
    {
      writer.flush();
    }
  }

}
