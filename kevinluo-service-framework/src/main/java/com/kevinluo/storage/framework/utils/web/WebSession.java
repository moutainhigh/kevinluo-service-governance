package com.kevinluo.storage.framework.utils.web;

/*
 * Creates on 2020/5/13.
 */

import javax.servlet.http.HttpSession;

/**
 * @author lts
 */
public class WebSession {

  public static HttpSession getSession()
  {
    return WebRequest.getSession();
  }

  public static String getAttribute(String name)
  {
    return String.valueOf(getSession().getAttribute(name));
  }

}