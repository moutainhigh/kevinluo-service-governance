package com.kevinluo.storage.framework.beans;

/*
 * Creates on 2019/11/13.
 */

/**
 * 符合Http响应请求的代码
 *
 * @author lts
 */
public interface HttpCode
{

  /**
   * 请求成功
   */
  int STATUS_200 = 200;

  /**
   * 1、语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求。
   * 2、请求参数有误。
   */
  int STATUS_400 = 400;

  /**
   * 服务请求超时
   */
  int STATUS_408 = 408;

  /**
   * 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。一般来说，这个问题都会在服务器的程序码出错时出现。
   */
  int STATUS_500 = 500;

}
