package com.kevinluo.storage.framework.logging.slf4j;

/*
 * creates on 2020/5/11 20:17.
 */

import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.thread.Threads;
import com.kevinluo.storage.framework.utils.StringUtils;
import org.slf4j.helpers.NOPLogger;

/**
 * @author lts
 */
public class Slf4jLog implements Log
{

  private org.slf4j.Logger log;

  /**
   * 为了避免日志打印堆栈跟踪造成系统性能创建对象时不必要的消耗
   * 所以这个StringBuilder持久化在当前类中
   */
  private static StringBuilder buildStack = new StringBuilder();

  public Slf4jLog()
  {
  }

  public Slf4jLog(org.slf4j.Logger log)
  {
    this.log = log;
  }

  @Override
  public boolean isBad()
  {
    return log instanceof NOPLogger;
  }

  @Override
  public boolean isDebugEnable()
  {
    return log.isDebugEnabled();
  }

  @Override
  public void info(Object msg)
  {
    log.info(stackMsg(msg));
  }

  @Override
  public void warn(Object msg)
  {
    log.warn(stackMsg(msg));
  }

  @Override
  public void error(Object msg)
  {
    log.error(stackMsg(msg));
  }

  @Override
  public void debug(Object msg)
  {
    log.debug(stackMsg(msg));
  }

  @Override
  public void info(Object msg, Object... formats)
  {
    log.info(stackMsg(msg), formats);
  }

  @Override
  public void warn(Object msg, Object... formats)
  {
    log.warn(stackMsg(msg), formats);
  }

  @Override
  public void error(Object msg, Object... formats)
  {
    log.error(stackMsg(msg), formats);
  }

  @Override
  public void debug(Object msg, Object... formats)
  {
    log.debug(stackMsg(msg), formats);
  }

  @Override
  public void error(Object msg, Throwable e)
  {
    log.error(stackMsg(msg), e);
  }

  @Override
  public void error(Object msg, Throwable e, StackTraceElement element)
  {
    log.error(stackMsg(msg, element), e);
  }

  @Override
  public void error(Object msg, Throwable e, Object... formats)
  {
    msg = StringUtils.format(String.valueOf(msg), formats);
    log.error(stackMsg(msg), e);
  }

  private String stackMsg(Object msg)
  {
    return stackMsg(msg, Threads.getStackTraceElement(4));
  }

  private String stackMsg(Object msg, StackTraceElement element)
  {
    String fileName = element.getFileName();
    String methodName = element.getMethodName();
    int lineNumber = element.getLineNumber();
    buildStack.append(".")
            .append(methodName)
            .append("(")
            .append(fileName)
            .append(":")
            .append(lineNumber)
            .append(") - ")
            .append(msg);
    String rs = buildStack.toString();
    buildStack.delete(0, buildStack.length());
    return rs;
  }

}
