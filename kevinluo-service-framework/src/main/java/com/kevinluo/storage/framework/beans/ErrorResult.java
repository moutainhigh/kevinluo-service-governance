package com.kevinluo.storage.framework.beans;

/*
 * Creates on 2020/5/11.
 */

/**
 * 错误返回信息
 *
 * @author lts
 */
public interface ErrorResult extends HttpCode
{

  /**
   * @return 错误标识
   */
  int getCode();

  /**
   * @return 建议处理方式
   */
  String getAdvice();

}