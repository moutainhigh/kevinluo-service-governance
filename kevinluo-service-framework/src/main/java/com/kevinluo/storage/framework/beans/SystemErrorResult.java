package com.kevinluo.storage.framework.beans;

/*
 * Creates on 2020/5/11.
 */

/**
 * @author lts
 */
public enum SystemErrorResult implements ErrorResult
{

  SERVICE_UNKNOWN(STATUS_500, "由于某些未知的错误,请求处理失败"),
  SERVICE_TIMEOUT(STATUS_408, "请求处理超时,请稍后再试"),
  SUCCESS(STATUS_200, "操作成功"),
  PARAMETER_ERROR(STATUS_400, "请求参数异常"),
  VERIFY_CODE_ERROR(STATUS_500, "验证码不正确或已过期, 请重试"),
  TOKEN_EXP(STATUS_500, "token不正确或已过期, 请重新登录"),
  UPLOAD_FILE_NULL(STATUS_500, "未获取到文件内容，请检查是否有上传文件"),
  ;

  int code;

  String advice;

  SystemErrorResult(int code, String advice)
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