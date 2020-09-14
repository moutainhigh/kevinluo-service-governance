package com.kevinluo.storage.framework.logging;

/*
 * creates on 2020/5/11 20:08.
 */

/**
 * @author lts
 */
public interface Log {

  /**
   * 当前日志对象是否是不可用的
   */
  boolean isBad();

  /**
   * 是否开启了debug
   */
  boolean isDebugEnable();

  void info(Object msg);

  void warn(Object msg);

  void error(Object msg);

  void debug(Object msg);

  void info(Object msg, Object... formats);

  void warn(Object msg, Object... formats);

  void error(Object msg, Object... formats);

  void debug(Object msg, Object... formats);

  void error(Object msg, Throwable e);

  void error(Object msg, Throwable e, StackTraceElement element);

  void error(Object msg, Throwable e, Object... formats);

}
