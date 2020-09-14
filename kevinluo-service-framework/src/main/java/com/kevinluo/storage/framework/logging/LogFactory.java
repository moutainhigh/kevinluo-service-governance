package com.kevinluo.storage.framework.logging;

/*
 * creates on 2020/5/11 20:16.
 */

import com.kevinluo.storage.framework.exception.LogException;
import com.kevinluo.storage.framework.logging.jdk.JdkAdapter;
import com.kevinluo.storage.framework.logging.slf4j.Slf4jLogAdapter;

import java.lang.reflect.Constructor;

/**
 * @author lts
 */
public class LogFactory {

  /**
   * {@code LogAdapter}构造器
   */
  private volatile static Constructor<? extends LogAdapter> loggingAdapterConstructor;

  static {
    tryFindLogImplementing(LogFactory::usingSlf4jLogAdapterLogging);
    tryFindLogImplementing(LogFactory::usingJdkLogAdapterLogging);
  }

  public static Log getLog(Class<?> key){
    return getLog(key.getName());
  }

  public static Log getLog(String key){
    Log log;
    try{
      log =loggingAdapterConstructor.newInstance().getLog(key);
    }catch (Exception e){
      throw new LogException("Create " + key + " logger failed, Cause: " + e.getMessage());
    }
    return log;
  }

  /**
   * 查找可用的日志框架
   */
  static void tryFindLogImplementing(Runnable runnable) {
    try {
      if (loggingAdapterConstructor == null) {
        runnable.run();
      }
    }catch (Exception e){
      // 忽略异常
    }
  }

  /** 使用slf4j做为当前日志框架 **/
  static void usingSlf4jLogAdapterLogging() {
    setLogImplementing(Slf4jLogAdapter.class);
  }

  /** 使用jdklog做为当前日志框架 **/
  static void usingJdkLogAdapterLogging() {
    setLogImplementing(JdkAdapter.class);
  }


  static void setLogImplementing(Class<? extends LogAdapter> implClass) {
    try{
      Constructor<? extends LogAdapter> constructor = implClass.getConstructor();
      Log log = constructor.newInstance().getLog(LogFactory.class);
      if(log.isBad()){
        // 当前日志框架不可用
        return;
      }
      log.debug("using " + implClass + " as the current project logger.");
      loggingAdapterConstructor = constructor;
    }catch (Exception e) {
      // 忽略异常，如果出了异常也是代表当前查找的日志框架不可用
    }
  }

}
