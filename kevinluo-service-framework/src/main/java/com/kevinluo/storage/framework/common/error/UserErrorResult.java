package com.kevinluo.storage.framework.common.error;

/*
 * Creates on 2019/11/13.
 */

import com.kevinluo.storage.framework.beans.ErrorResult;

/**
 * @author lts
 */
public enum UserErrorResult implements ErrorResult
{

  PASSWORD_CANNOT_NULL(STATUS_500, "用户密码不能为空"),
  EMAIL_FORMAT_ERROR(STATUS_400, "邮箱格式有误, 请检查邮箱格式"),
  REGISTER_FAILURE_USERNAME_CONFLICT(STATUS_500, "用户名被占用, 请重新填写"),
  USERNAME_CANNOT_GE_SIX_ORNULL(STATUS_400, "用户名不能为空或长度不能大于20位"),
  PASSWORD_CANNOT_LT_SIX_ORNULL(STATUS_500, "用户密码不能小于6位");

  int code;

  String advice;

  UserErrorResult(int code, String advice)
  {
    this.code = code;
    this.advice = advice;
  }

  @Override
  public int getCode()
  {
    return code;
  }

  @Override
  public String getAdvice()
  {
    return advice;
  }

}
