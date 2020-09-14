package com.kevinluo.storage.framework.utils;

/*
 * Creates on 2019/11/13.
 */

import com.kevinluo.storage.framework.beans.ErrorResult;
import com.kevinluo.storage.framework.beans.SystemErrorResult;
import com.kevinluo.storage.framework.common.error.UserErrorResult;
import com.kevinluo.storage.framework.exception.BusinessException;

/**
 * @author lts
 */
public class Assert
{

  /**
   * 邮箱验证正则表达式
   */
  static final String EMAIL_REGEX = "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}";

  /**
   * 检查邮箱是否符合规范
   *
   * @param email 邮箱地址
   */
  public static void checkMail(String email)
  {
    if (!(email != null && email.matches(EMAIL_REGEX)))
    {
      throw new BusinessException(UserErrorResult.EMAIL_FORMAT_ERROR);
    }
  }

  /**
   * 非空判断
   *
   * @param t 对象实例
   */
  public static <T> void requiredNonNull(T t)
  {
    requiredNonNull(t, t.getClass().getSimpleName());
  }

  /**
   * 非空判断
   *
   * @param t           对象实例
   * @param errorResult 异常返回信息
   */
  public static <T> T requiredNonNull(T t, ErrorResult errorResult)
  {
    return requiredNonNull(t, errorResult.getAdvice());
  }

  /**
   * 非空判断
   *
   * @param t       对象实例
   * @param message 报错信息
   */
  public static <T> T requiredNonNull(T t, String message)
  {
    if (t == null)
      throw new NullPointerException(message);
    return t;
  }

  /**
   * 不能为false
   *
   * @param value  布尔值
   * @param result 如果为false返回信息
   */
  public static void notFalse(boolean value, ErrorResult result)
  {
    if (!value)
      throw new BusinessException(result);
  }

  /**
   * 检查登录token是否为空
   */
  public static <T> T loginTokenNonNull(T token)
  {
    return requiredNonNull(token, SystemErrorResult.TOKEN_EXP);
  }

}
