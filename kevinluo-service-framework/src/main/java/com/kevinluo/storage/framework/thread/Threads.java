package com.kevinluo.storage.framework.thread;

/*
 * Creates on 2020/3/24.
 */

import lombok.SneakyThrows;

/**
 * 静态{@code Thread}工具。
 *
 * @author lts
 */
public class Threads {

  /**
   * 获取方法的调用者。
   */
  @SneakyThrows
  public static Class<?> getCaller() {
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    String classname = elements[elements.length - 1].getClassName();
    return Class.forName(classname);
  }

  /**
   * 获取堆栈调用信息
   * @return
   */
  public static StackTraceElement getStackTraceElement() {
    return getStackTraceElement(1);
  }

  public static StackTraceElement getStackTraceElement(int n){
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    return elements[n];
  }

  /**
   * 获取调用者的类加载器
   *
   * @return
   */
  public static ClassLoader getCallerLoader() {
    return getCaller().getClassLoader();
  }

}
